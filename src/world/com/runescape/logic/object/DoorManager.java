package com.runescape.logic.object;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.runescape.Constants;
import com.runescape.Static;
import com.runescape.content.handler.ActionHandlerSystem;
import com.runescape.content.handler.ObjectOptionHandler;
import com.runescape.engine.event.DelayedEvent;
import com.runescape.logic.Locatable;
import com.runescape.logic.map.PathProcessor;
import com.runescape.logic.map.Region;
import com.runescape.logic.map.Tile;
import com.runescape.logic.player.Player;
import com.runescape.utility.Logging;

import org.apache.log4j.Logger;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.*;

public class DoorManager implements ObjectOptionHandler {

    private static final Logger logger = Logging.log();

    public enum DoorState { OPENED, CLOSED }

    public static class SpecificDoorDefinition {
        public int changedDir;
        public int xOffset;
        public int yOffset;

        public SpecificDoorDefinition(int changedDir, int xOffset, int yOffset) {
            this.changedDir = changedDir;
            this.xOffset = xOffset;
            this.yOffset = yOffset;
        }
    }

    public static class DoorDefinition {
        public int defaultId;
        public int changedId;
        public DoorState defaultState;
        public boolean lockPick;
        public boolean instantClose;
        public int dirType;
        public int tiedId;
        public Map<Tile, SpecificDoorDefinition> specifics;

        public DoorDefinition(
                int defaultId, int changedId, DoorState defaultState,
                boolean lockPick, boolean instantClose, int dirType,
                int tiedId, Map<Tile, SpecificDoorDefinition> specifics
        ) {
            this.defaultId = defaultId;
            this.changedId = changedId;
            this.defaultState = defaultState;
            this.lockPick = lockPick;
            this.instantClose = instantClose;
            this.dirType = dirType;
            this.tiedId = tiedId;
            this.specifics = specifics;
        }
    }

    public class Door extends Locatable {
        public DoorDefinition def;
        public DoorState state;

        public int dirOffset;
        public int xOffset;
        public int yOffset;

        public Door tiedDoor;
        public Tile tiedLoc;

        public long lastChange;

        public Door(DoorDefinition def, GameObject obj) {
            this.def = def;
            this.state = def.defaultState;
            SpecificDoorDefinition override = def.specifics != null ?
                    def.specifics.get(obj.getLocation()) : null;
            if (override != null) {
                this.dirOffset = override.changedDir - obj.getDirection();
                this.xOffset = override.xOffset;
                this.yOffset = override.yOffset;
            } else {
                computeAutoOffsets(obj);
            }
            setLocation(obj.getLocation());
        }

        private void computeAutoOffsets(GameObject obj) {
            this.dirOffset = 1;
            switch (obj.getDirection()) {
                case 0:
                    xOffset = -1;
                    break;
                case 1:
                    yOffset = 1;
                    break;
                case 2:
                    xOffset = 1;
                    break;
                case 3:
                    yOffset = -1;
                    break;
            }
            boolean flip = (def.defaultState == DoorState.OPENED || def.dirType == 1);
            if (flip) {
                dirOffset = -dirOffset;
                if (def.defaultState == DoorState.OPENED) {
                    xOffset = -xOffset;
                    yOffset = -yOffset;
                }
            }
        }

        public void toggle(Tile newLoc, boolean repeated) {
            state = (state == DoorState.CLOSED ? DoorState.OPENED : DoorState.CLOSED);
            lastChange = System.currentTimeMillis();
            if (tiedDoor != null && tiedLoc != null && !repeated) {
                GameObject tiedObj = Region.getObject(tiedLoc);
                if (tiedObj != null) {
                    changeDoor(tiedDoor, tiedObj, true);
                }
            }
            setLocation(newLoc);
        }
    }
    
    private final Map<Integer, DoorDefinition> definitions = new HashMap<>();
    private final Map<Tile, Door> liveDoors = new HashMap<>();

    public void load() {
        try {
            String path = Static.parseString(Constants.DATA_FOLDER + "/world/map/doors.json");
            FileReader reader = new FileReader(path);
            Type listType = new TypeToken<List<JsonDoor>>() {}.getType();
            List<JsonDoor> jsonList = new Gson().fromJson(reader, listType);
            int count = 0;
            for (JsonDoor jd : jsonList) {
                DoorDefinition def = convertJson(jd);
                definitions.put(def.defaultId, def);
                count++;
            }
            load(Static.ahs);
            logger.info("DoorManager: Loaded " + count + " door definitions.");
        } catch (Exception e) {
            logger.error("DoorManager JSON load failed!", e);
        }
    }

    private DoorDefinition convertJson(JsonDoor jd) {
        DoorState state = jd.defaultState.equalsIgnoreCase("opened")
                ? DoorState.OPENED : DoorState.CLOSED;
        Map<Tile, SpecificDoorDefinition> map = null;
        if (jd.specific != null && !jd.specific.isEmpty()) {
            map = new HashMap<>();
            for (JsonSpecific s : jd.specific) {
                Tile t = Tile.locate(s.tile.x, s.tile.y, s.tile.z);
                SpecificDoorDefinition sd = new SpecificDoorDefinition(
                        s.changedDir, s.xOffset, s.yOffset
                );
                map.put(t, sd);
            }
        }

        return new DoorDefinition(
                jd.defaultId,
                jd.changedId,
                state,
                jd.lockPick,
                jd.instantClose,
                jd.dirType,
                jd.tiedId,
                map
        );
    }
    
    @Override
    public void load(ActionHandlerSystem system) {
        int[] ids = definitions.keySet().stream().mapToInt(i -> i).toArray();
        system.registerObjectOptionHandler(ids, this);
        List<Integer> changed = new ArrayList<>();
        for (DoorDefinition d : definitions.values()) {
            if (!d.instantClose)
                changed.add(d.changedId);
        }
        system.registerObjectOptionHandler(changed.stream().mapToInt(i -> i).toArray(), this);
    }

    @Override
    public boolean explicitlyForMembers() {
        return false;
    }

    @Override
    public void handleObjectOption1(Player p, GameObject obj) {
        useDoor(p, obj, false);
    }

    @Override
    public void handleObjectOption2(Player p, GameObject obj) {}
    @Override
    public void handleObjectOption3(Player p, GameObject obj) {}

    public void loadRegion(Region r) {
        if (r.getObjects() == null)
            return;
        for (int z = 0; z < 4; z++) {
            GameObject[][] plane = r.getObjects()[z];
            if (plane == null) continue;
            for (int x = 0; x < 64; x++)
                for (int y = 0; y < 64; y++)
                    spawnDoor(plane[x][y]);
        }
    }

    private void spawnDoor(GameObject obj) {
        if (obj == null)
            return;
        DoorDefinition def = definitions.get(obj.getId());
        if (def == null)
            return;
        Door door = new Door(def, obj);
        liveDoors.put(obj.getLocation(), door);
        if (door.xOffset != 0 || door.yOffset != 0) {
            liveDoors.put(obj.getLocation().translate(door.xOffset, door.yOffset, 0), door);
        }
        if (def.tiedId != -1) {
            searchNearbyForTiedDoor(obj, door);
        }
    }

    private void searchNearbyForTiedDoor(GameObject obj, Door door) {
        for (int dx = -2; dx <= 2; dx++) {
            for (int dy = -2; dy <= 2; dy++) {
                Tile check = Tile.locate(obj.getX() + dx, obj.getY() + dy, obj.getZ());
                GameObject other = Region.getObject(check);
                if (other != null && other.getId() == door.def.tiedId) {
                    Door d2 = liveDoors.get(other.getLocation());
                    if (d2 != null) {
                        door.tiedDoor = d2;
                        door.tiedLoc = other.getLocation();

                        d2.tiedDoor = door;
                        d2.tiedLoc = obj.getLocation();
                    }
                }
            }
        }
    }
    
    public void useDoor(Player p, GameObject obj, boolean picked) {
        Door door = liveDoors.get(obj.getLocation());
        if (door == null) {
        	logger.warn("Encountered nulled door [id=" + obj.getId() + ", loc=[" + obj.getLocation() + "]]");
            return;
        }
        
        logger.warn("Door [id=" + obj.getId() + ", loc=[" + obj.getLocation() + "], dir=" + obj.getDirection() + "]"); 
        
        if (door.def.lockPick && !picked && door.state == DoorState.CLOSED) {
            p.sendMessage("This door is locked.");
            return;
        }
        if (door.def.instantClose) {
            handleInstantDoor(p, obj, door);
        } else {
            if (System.currentTimeMillis() - door.lastChange >= Constants.GAME_TICK_INTERVAL)
                changeDoor(door, obj, false);
        }
    }

    private void handleInstantDoor(Player p, GameObject obj, Door door) {
        int offX = 0;
        int offY = 0;
        switch (obj.getDirection()) {
            case 0:
                offX = (p.getX() < obj.getX()) ? 1 : -1;
                break;
            case 1:
                offY = (p.getY() > obj.getY()) ? -1 : 1;
                break;
            case 2:
                offX = (p.getX() > obj.getX()) ? -1 : 1;
                break;
            case 3:
                offY = (p.getY() < obj.getY()) ? 1 : -1;
                break;
        }
        Tile dest = p.getLocation().translate(offX, offY, 0);
        p.getPathProcessor().reset(true);
        p.getPathProcessor().setMoveSpeed(PathProcessor.MOVE_SPEED_WALK);
        p.getPathProcessor().add(dest);
        if (door.state == DoorState.CLOSED)
            changeDoor(door, obj, false);
        Static.engine.submit(new DelayedEvent(1200) {
            @Override
            public void run() {
                if (door.state == DoorState.OPENED) {
                    changeDoor(door, Region.getObject(door.getLocation()), false);
                    p.getPathProcessor().reset(true);
                }
            }
        });
    }
    
    private void changeDoor(Door door, GameObject obj, boolean repeated) {
        int newId = (door.state == door.def.defaultState)
                ? door.def.changedId
                : door.def.defaultId;
        int newDir = (door.state == door.def.defaultState)
                ? ((obj.getDirection() + door.dirOffset) & 0x3)
                : ((obj.getDirection() - door.dirOffset) & 0x3);
        int newX = (door.state == door.def.defaultState)
                ? obj.getX() + door.xOffset
                : obj.getX() - door.xOffset;
        int newY = (door.state == door.def.defaultState)
                ? obj.getY() + door.yOffset
                : obj.getY() - door.yOffset;
        Tile newLoc = Tile.locate(newX, newY, obj.getZ());
        door.toggle(newLoc, repeated);
        Static.world.getObjectManager().remove(obj.getLocation());
        Static.world.getObjectManager().add(newId, newLoc, obj.getType(), newDir);
    }
    
    public static class JsonDoor {
        public int defaultId;
        public int changedId;
        public String defaultState;
        public boolean lockPick;
        public boolean instantClose;
        public int dirType;
        public int tiedId;
        public List<JsonSpecific> specific;
    }

    public static class JsonSpecific {
        public JsonTile tile;
        public int changedDir;
        public int xOffset;
        public int yOffset;
    }

    public static class JsonTile {
        public int x, y, z;
    }
}

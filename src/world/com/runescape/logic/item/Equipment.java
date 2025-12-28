package com.runescape.logic.item;

import com.runescape.Static;
import com.runescape.logic.player.Player;

public class Equipment {

    /**
     * Wield an item from the player's inventory into their equipment.
     */
    public static void wieldEquipment(Player player, int id, int index) {
        PossesedItem item = player.getInventory().get(index);
        if (item == null || item.getId() != id) return;

        item = item.clone();
        ItemDefinition def = item.getDefinition();
        if (def == null) return;
        EquipmentDefinition equipDef = def.getEquipmentDefinition();
        if (equipDef == null) {
            Static.proto.sendMessage(player, "You can't wear that.");
            return;
        }

        // Check requirements
        if (!player.getLevels().meetsRequirements(def, true)) return;
        // Handle 2H weapon swap logic
        if (equipDef.getEquipmentType() == EquipmentDefinition.EquipmentType.WEAPON_2H) {
            PossesedItem curWep = player.getEquipment().get(EquipmentDefinition.SLOT_WEAPON);
            PossesedItem curShield = player.getEquipment().get(EquipmentDefinition.SLOT_SHIELD);

            if (curWep != null && curShield != null) {
                if (player.getInventory().remaining() < 1) {
                    Static.proto.sendMessage(player, "Not enough inventory space.");
                    return;
                }
                player.getInventory().remove(item, index, false);
                player.getInventory().add(curWep, index, true);
                player.getInventory().add(curShield);
                player.getEquipment().remove(curWep);
                player.getEquipment().remove(curShield);
                player.getEquipment().add(item, equipDef.getEquipmentType().getSlot());
                return;
            } else if (curWep != null) {
                player.getInventory().remove(item, index, false);
                player.getInventory().add(curWep, index, true);
                player.getEquipment().remove(curWep);
                player.getEquipment().add(item, equipDef.getEquipmentType().getSlot());
                return;
            } else if (curShield != null) {
                player.getInventory().remove(item, index, false);
                player.getInventory().add(curShield, index, true);
                player.getEquipment().remove(curShield);
                player.getEquipment().add(item, equipDef.getEquipmentType().getSlot());
                return;
            }
        }

        // Handle shields with 2H weapon
        if (equipDef.getEquipmentType() == EquipmentDefinition.EquipmentType.SHIELD) {
            PossesedItem curWep = player.getEquipment().get(EquipmentDefinition.SLOT_WEAPON);
            if (curWep != null &&
                curWep.getDefinition().getEquipmentDefinition().getEquipmentType() == EquipmentDefinition.EquipmentType.WEAPON_2H) {

                player.getInventory().remove(item, index, false);
                player.getInventory().add(curWep, index, true);
                player.getEquipment().remove(curWep);
                player.getEquipment().add(item, equipDef.getEquipmentType().getSlot());
                return;
            }
        }
        // Standard swap logic
        PossesedItem curItem = player.getEquipment().get(equipDef.getEquipmentType().getSlot());
        player.getInventory().remove(item, index, curItem == null);
        if (curItem != null && !(id == curItem.getId() && def.isStackable())) {
            player.getInventory().add(curItem, index);
            player.getEquipment().remove(curItem, equipDef.getEquipmentType().getSlot(), false);
        }
        player.getEquipment().add(item, equipDef.getEquipmentType().getSlot(), true);
    }

    /**
     * Remove an equipped item and put it into the inventory if possible.
     */
    public static void removeEquipment(Player player, int id) {
        ItemDefinition def = ItemDefinition.forId(id);
        EquipmentDefinition equipDef = def.getEquipmentDefinition();

        PossesedItem item = player.getEquipment().get(equipDef.getEquipmentType().getSlot());
        if (item == null || item.getId() != id) return;

        if (player.getInventory().add(item)) {
            player.getEquipment().remove(item, equipDef.getEquipmentType().getSlot());
        }
    }

    /**
     * Forcefully remove an equipped item, ignoring inventory space.
     *
     * @return true if item was added to inventory successfully, false otherwise
     */
    public static boolean forceRemoveEquipment(Player player, int id) {
        ItemDefinition def = ItemDefinition.forId(id);
        EquipmentDefinition equipDef = def.getEquipmentDefinition();

        PossesedItem item = player.getEquipment().get(equipDef.getEquipmentType().getSlot());
        if (item == null || item.getId() != id) return false;

        player.getEquipment().remove(item, equipDef.getEquipmentType().getSlot());
        return player.getInventory().add(item);
    }
}

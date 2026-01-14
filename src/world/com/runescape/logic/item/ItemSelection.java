package com.runescape.logic.item;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import com.runescape.Static;
import com.runescape.adapter.protocol.cache.format.VarbitDefinitonAdapter;
import com.runescape.logic.player.Player;

public class ItemSelection<T> {

    //Varpbits
    private static final int SELECTED_AMOUNT = 8095;
    private static final int MAXIMUM_AMOUNT = 8094;

    public static ItemSelection<Integer> itemIdSelection() {
        return new ItemSelection<Integer>().display(Function.identity());
    }

    private Type type = Type.MAKE;
    private int maxAmount = 1;
    private List<T> options;
    private BiConsumer<T, Integer> action;
    private Function<T, Integer> displayIdExtractor;

    public ItemSelection() {

    }

    public ItemSelection(Type type, int maxAmount, Function<T, Integer> displayIdExtractor, BiConsumer<T, Integer> action, List<T> options) {
        this.type = type;
        this.maxAmount = maxAmount;
        this.options = options;
        this.action = action;
        this.displayIdExtractor = displayIdExtractor;
    }

    public void send(Player player) {
    	Static.proto.sendInterface(player, 905, 752, 13, true);
    	Static.proto.sendInterface(player, 916, 905, 4, true);
    	Static.proto.sendString(player, 916, 1, type.infoText);
    	Static.proto.sendInterfaceVariable(player, 754, type.ordinal());
    	
        player.varps().setVarBit(player, MAXIMUM_AMOUNT, maxAmount);
        player.varps().setVarBit(player, SELECTED_AMOUNT, maxAmount);
        for(int i = 0; i < SLOTS.length; i++) {
            Slot slot = SLOTS[i];
            if(i >= options.size()) {
                //PacketSender.sendVarc(player, slot.varcId, -1);
            	Static.proto.sendInterfaceVariable(player, slot.varcId, -1);
                continue;
            }
            T item = options.get(i);
            int displayId = displayIdExtractor.apply(item);
            Static.proto.sendInterfaceVariable(player, slot.varcId, displayId);
            ItemDefinition def = ItemDefinition.forId(displayId);
            String name = def != null ? def.name : "Unknown";
            Static.proto.sendSpecialString(player, slot.stringId, name);
        }
        Static.proto.sendAccessMask(player, -1, 0, 916, 8, 0, 0);
        Static.proto.sendAccessMask(player, -1, 0, 905, 6, 0, 0);
       // PacketSender.sendAccessMask(player, -1, -1, 905, 6, type.allowAllAndCustom ? 1 : 0);
        //PacketSender.sendAccessMask(player, -1, -1, 916, 8, type.allowAllAndCustom ? 1 << 1 : 0);
    }

    public void selected(Player player, int slot) {
        if (slot < 0 || slot >= options.size()) {
            return;
        }
        T selected = options.get(slot);
        int amount = Math.max(1, Math.min(player.varps().getValue(SELECTED_AMOUNT), player.varps().getValue(MAXIMUM_AMOUNT)));
        //int amount = Math.max(1, Math.min(VarbitDefinitonAdapter.getClientVarpBitDefinitions(player, SELECTED_AMOUNT), VarbitDefinitonAdapter.getClientVarpBitDefinitions(player, MAXIMUM_AMOUNT)));
        close(player);
        action.accept(selected, amount);
    }

    public void customAmount(Player player) {
       /* player.setAmountScript("Enter amount:", value -> {
            Integer amt = (Integer) value;
            send(player);
            setAmount(player, amt);
            return true;
        });*/
    }

    public void setAmount(Player player, int amt) {
        //amt = Math.max(1, Math.min(amt, VarbitDefinitonAdapter.getClientVarpBitDefinitions(player, MAXIMUM_AMOUNT)));
        player.varps().setVarBit(player, SELECTED_AMOUNT, amt); //setVar
        //VarbitDefinitonAdapter.setVar(SELECTED_AMOUNT, amt);
    }

    public void incrementAmount(Player player, int increment) {
        setAmount(player, player.varps().getValue(SELECTED_AMOUNT) + increment);
    }

    public void handleButton(Player player, int buttonId) {
        switch (buttonId) {
            case 5:
                setAmount(player, 1);
                break;
            case 6:
                setAmount(player, 5);
                break;
            case 7:
                setAmount(player, 10);
                break;
            case 8:
                setAmount(player, player.varps().getValue(MAXIMUM_AMOUNT));
                break;
            case 19:
                incrementAmount(player, 1);
                break;
            case 20:
                incrementAmount(player, -1);
                break;
        }
    }

    public ItemSelection<T> type(Type type) {
        this.type = type;
        return this;
    }

    public ItemSelection<T> maxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
        return this;
    }

    public ItemSelection<T> options(T... options) {
        this.options = Arrays.asList(options);
        return this;
    }

    public ItemSelection<T> options(List<T> options) {
        this.options = options;
        return this;
    }

    public ItemSelection<T> singleOption(T option) {
        this.options = Collections.singletonList(option);
        return this;
    }

    public ItemSelection<T> action(BiConsumer<T, Integer> action) {
        this.action = action;
        return this;
    }

    public ItemSelection<T> display(Function<T, Integer> displayIdExtractor) {
        this.displayIdExtractor = displayIdExtractor;
        return this;
    }

    public void close(Player player) {
        player.itemSelection = null;
      //  PacketSender.removeChatBoxInterface(player);
        Static.proto.sendCloseChatboxInterface(player);
    }

    enum Type {
        //hardcoded in the cs. ordinal = varp value
        MAKE,
        MAKE_SETS(false),
        COOK,
        ROAST,
        OFFER,
        SELL,
        BAKE,
        CUT,
        DEPOSIT,
        MAKE_NO_ALL(false),
        TELEPORT,
        SELECT,
        MAKE_SETS_NO_ALL(false),
        TAKE,
        RETURN,
        HEAT,
        ADD
        ;

        public String infoText;

        Type() {
            this(true);
        }

        Type(boolean allowAllAndCustom) {
            this.allowAllAndCustom = allowAllAndCustom;
            infoText = "Choose an amount, then click on an item to begin.";
        }

        private boolean allowAllAndCustom;
    }

    private static final Slot[] SLOTS = {
            new Slot(755, 132),
            new Slot(756, 133),
            new Slot(757, 134),
            new Slot(758, 135),
            new Slot(759, 136),
            new Slot(760, 137),

            new Slot(1139, 280),
            new Slot(1140, 281),
            new Slot(1141, 282),
            new Slot(1142, 283),

            new Slot(120, 275),
            new Slot(185, 316),
            new Slot(87, 317),
            new Slot(90, 318),
    };
    private static class Slot {

        public Slot(int varcId, int stringId) {
            this.varcId = varcId;
            this.stringId = stringId;
        }

        int varcId;
        int stringId;

    }
}

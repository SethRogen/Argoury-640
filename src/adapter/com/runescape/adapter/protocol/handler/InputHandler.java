package com.runescape.adapter.protocol.handler;

import com.runescape.Constants;
import com.runescape.logic.input.PlayerInput;
import com.runescape.logic.input.impl.BankDepositXInput;
import com.runescape.logic.input.impl.BankWithdrawXInput;
import com.runescape.logic.input.impl.ClanPrefixInput;
import com.runescape.logic.input.impl.TradingOfferXInput;
import com.runescape.logic.input.impl.TradingRemoveXInput;
import com.runescape.logic.player.Player;
import com.runescape.network.Frame;
import com.runescape.network.handler.PlayerFrameHandler;

import org.apache.mina.core.session.IoSession;

import java.util.HashMap;
import java.util.Map;

public class InputHandler extends PlayerFrameHandler {

    private final Map<Integer, PlayerInput> inputHandlers = new HashMap<>();

    public InputHandler() {
    	inputHandlers.put(Constants.Inputs.CC_PREFIX, new ClanPrefixInput());
    	inputHandlers.put(Constants.Inputs.BANK_WITHDRAW_X, new BankWithdrawXInput());
    	inputHandlers.put(Constants.Inputs.BANK_DEPOSIT_X, new BankDepositXInput());
    	inputHandlers.put(Constants.Inputs.TRADING_OFFER_X, new TradingOfferXInput());
        inputHandlers.put(Constants.Inputs.TRADING_REMOVE_X, new TradingRemoveXInput());
    }

    @Override
    public void handleFrame(Player player, IoSession session, Frame frame) {
        int inputId = player.getAttributes().getInt("inputId");
        Object input;

        switch (frame.getOpcode()) {
        case 54:
            input = frame.readString();
            break;
        case 51:
            input = frame.readInt();
            break;
        default:
            return;
    }

        PlayerInput handler = inputHandlers.get(inputId);
        if (handler != null) {
            handler.handle(player, input);
        } else {
            System.err.println("Unhandled input id: " + inputId);
        }

        player.getAttributes().unSet("inputId");
    }
}

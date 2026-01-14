package com.runescape.content.handler;

public interface ActionHandler {
	
    public void load(ActionHandlerSystem system) throws Exception;

    public boolean explicitlyForMembers();
}

package com.runescape.content.skills.cooking;

import com.runescape.logic.object.GameObject;

public class CookType {

    public final CookData data;
    public final GameObject fireObject;
    public boolean burnedFire = false;
    public CookType(CookData data, GameObject fireObject) {
        this.data = data;
        this.fireObject = fireObject;
    }

}

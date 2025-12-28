package com.runescape.adapter;

import com.runescape.engine.login.LoginResponse;

public interface ClientConfiguration {
    public int getClientVersion();

    public int getLoginResponseCode(LoginResponse resp);

    public LoginResponse getLoginResponseForCode(int code);

    public int[] getFrameLengths();
}

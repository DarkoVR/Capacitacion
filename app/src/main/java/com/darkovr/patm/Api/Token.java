package com.darkovr.patm.Api;

/**
 * Created by marco on 13/03/18.
 */

public class Token {
    static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Token.token = token;
    }
}

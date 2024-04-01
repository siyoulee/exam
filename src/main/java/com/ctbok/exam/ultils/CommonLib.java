package com.ctbok.exam.ultils;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;


public class CommonLib {
    private static final String SING = "==--Ct88**88--==";

    public static String getHistoryStatus(String token){
        DecodedJWT verify = com.auth0.jwt.JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        return verify.getClaim("historyStatus").asString();
    }

}

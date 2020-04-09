package com.hcb.newBilly.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class JwtUtils {
    private static Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private static String SECRET = "billSystem";

    /**
     * 验证
     */
    public static boolean checkUser(String token, String userAccount) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withClaim("userAccount", userAccount)
                    .build();
            jwtVerifier.verify(token);
            logger.info("jwt验证成功");
            return true;
        } catch (Exception e) {
            logger.info("jwt验证失败");
            return false;
        }
    }

    /**
     * 得到userId
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT decode = JWT.decode(token);
            String userId = decode.getClaim("userId").asString();
            return userId;
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 得到userId
     */
    public static String getUserAccount(String token) {
        try {
            DecodedJWT decode = JWT.decode(token);
            String userAccount = decode.getClaim("userAccount").asString();
            return userAccount;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 生成token
     */
    public static String getJwtToken(String userAccount, String userId) {
        try {
            Date date = new Date(System.currentTimeMillis() + 30 * 60 * 1000);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            String sign = JWT.create()
                    .withClaim("userAccount", userAccount)
                    .withClaim("userId", userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
            return sign;
        } catch (Exception e) {
            return null;
        }
    }
}

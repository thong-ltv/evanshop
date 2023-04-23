package com.evanshop.controller.web;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class TokenGenerator {
	private static final String HMAC_ALGORITHM = "HmacSHA256";
    private static final String SECRET_KEY = "your-secret-key";

    public static String generateToken(String username, long timestamp) {
        try {
            Mac hmac = Mac.getInstance(HMAC_ALGORITHM);
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), HMAC_ALGORITHM);
            hmac.init(secretKeySpec);
            String data = username + ":" + timestamp;
            byte[] mac = hmac.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(mac);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to generate token", e);
        }
    }
}

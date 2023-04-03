package com.shorterurl.authservice.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Base64;

public class JwtConfig {
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
    private static final SecretKey JWT_SECRET_KEY = Keys.secretKeyFor(SIGNATURE_ALGORITHM);

    public static SecretKey getJwtSecretKey() {
        return JWT_SECRET_KEY;
    }

    public static String getEncodedJwtSecret() {
        return Base64.getEncoder().encodeToString(JWT_SECRET_KEY.getEncoded());
    }
}


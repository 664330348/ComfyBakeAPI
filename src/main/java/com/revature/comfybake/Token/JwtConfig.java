package com.revature.comfybake.Token;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.Key;
import java.util.Properties;

@Component
public class JwtConfig {
    private int expiration = 5 * 60 * 60 * 1000; // # of ms in 5 hours
    private final SignatureAlgorithm sigAlg = SignatureAlgorithm.HS256;
    private Key signingKey;

    Properties props = new Properties();

    public JwtConfig(){
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            props.load(loader.getResourceAsStream("application.properties"));
            byte[] saltyBytes = DatatypeConverter.parseBase64Binary(props.getProperty("jwt-salt"));
            signingKey = new SecretKeySpec(saltyBytes, sigAlg.getJcaName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public SignatureAlgorithm getSigAlg() {
        return sigAlg;
    }

    public Key getSigningKey() {
        return signingKey;
    }

    public void setSigningKey(Key signingKey) {
        this.signingKey = signingKey;
    }
}

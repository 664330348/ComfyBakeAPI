package com.revature.comfybake.Token;

import com.revature.comfybake.Principal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenService {
    private JwtConfig jwtConfig;

    @Autowired
    public TokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(Principal subject) {
        long now = System.currentTimeMillis(); // number of milliseconds passed since UNIX time
        JwtBuilder tokenBuilder = Jwts.builder()
                .setId(subject.getUserId())
                .setIssuer("bake")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration()))
                .setSubject(subject.getUsername())
                .claim("role", subject.getRole())
                .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());

        return tokenBuilder.compact();
    }

    public boolean isTokenValid(String token){
        return extractRequesterDetails(token) != null;
    }

    public Principal extractRequesterDetails(String token) {
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();

            Principal principal = new Principal();
            principal.setUserId(claims.getId());
            principal.setUsername(claims.getSubject());
            principal.setRole(claims.get("role", String.class));
            return principal;
        } catch (Exception e){
            return null; // todo consider if this is the best exception
        }
    }


}

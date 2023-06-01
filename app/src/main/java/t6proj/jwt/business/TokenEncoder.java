package t6proj.jwt.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import t6proj.jwt.dto.Token;
import t6proj.jwt.exceptions.InvalidTokenException;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

@Component
public class TokenEncoder {
    private String jwtSecret = "niIEbC8bRTTEMmyXxgpBMxrDZJ354VtZUC9BVUBV1I7q3EinWDQBas";

    public String issue(Token token)
    {
        var mapper = new ObjectMapper();
        try {
            var tokenPayload = mapper.writeValueAsString(token);
            return Jwts.builder().setSubject(tokenPayload).signWith(this.getHmacKey()).compact();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Key getHmacKey() {
        return new SecretKeySpec(
                Base64.getDecoder().decode(this.jwtSecret),
                SignatureAlgorithm.HS256.getJcaName()
        );
    }

    public Token decode(String token) throws InvalidTokenException {
        var subject = Jwts.parserBuilder()
                .setSigningKey(this.getHmacKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        var objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(subject, Token.class);
        } catch (JsonProcessingException e) {
            throw new InvalidTokenException();
        }
    }
}

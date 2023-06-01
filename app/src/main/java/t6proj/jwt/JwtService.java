package t6proj.jwt;

import org.springframework.stereotype.Service;
import t6proj.jwt.business.TokenEncoder;
import t6proj.jwt.dto.Token;
import t6proj.jwt.exceptions.InvalidTokenException;

@Service
public class JwtService {
    private final TokenEncoder tokenEncoder;

    public JwtService(
            TokenEncoder tokenEncoder
    ) {
        this.tokenEncoder = tokenEncoder;
    }

    public String issueJwtToken(Token token) {
        return this.tokenEncoder.issue(token);
    }

    public Token decodeJwtToken(String token) throws InvalidTokenException {
        return this.tokenEncoder.decode(token);
    }
}

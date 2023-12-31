package t6proj.authentication.business;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import t6proj.authentication.dto.AuthenticationRequest;
import t6proj.authentication.dto.AuthenticationResult;
import t6proj.jwt.JwtService;
import t6proj.jwt.dto.Token;
import t6proj.user.UserService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Component
public class AuthenticationHandler {
    private final UserService userService;
    private final JwtService jwtService;

    public AuthenticationHandler(
            UserService userService,
            JwtService jwtService
    ) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @SneakyThrows
    public AuthenticationResult handle(AuthenticationRequest request)
    {
        var user = this.userService.getUserByEmail(request.email);
        if (user == null) {
            return new AuthenticationResult(false, null);
        }

        var digest = MessageDigest.getInstance("SHA-256");
        var hashedPassword = new String(
                Base64.getEncoder().encode(digest.digest(request.password.getBytes(StandardCharsets.UTF_8))),
                StandardCharsets.UTF_8
        );
        if (!user.password.equals(hashedPassword)) {
            return new AuthenticationResult(false, null);
        }

        var expirationTime = Instant.now().plusSeconds(60 * 24 * 30);
        var token = new Token();
        token.userId = user.id;
        token.expirationDate = new Date(expirationTime.toEpochMilli());

        return new AuthenticationResult(true, this.jwtService.issueJwtToken(token));
    }
}

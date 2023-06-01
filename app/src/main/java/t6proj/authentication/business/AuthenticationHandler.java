package t6proj.authentication.business;

import org.springframework.stereotype.Component;
import t6proj.authentication.dto.AuthenticationRequest;
import t6proj.authentication.dto.AuthenticationResult;
import t6proj.jwt.JwtService;
import t6proj.jwt.dto.Token;
import t6proj.user.UserService;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Objects;

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

    public AuthenticationResult handle(AuthenticationRequest request)
    {
        var user = this.userService.getUserByEmail(request.email);
        if (user == null) {
            return new AuthenticationResult(false, null);
        }

        // @todo replace with hashing algorithm
        if (!user.password.equals(request.password)) {
            return new AuthenticationResult(false, null);
        }

        var expirationTime = Instant.now().plusSeconds(60 * 24 * 30);
        var token = new Token();
        token.userId = user.id;
        token.expirationDate = new Date(expirationTime.toEpochMilli());

        return new AuthenticationResult(true, this.jwtService.issueJwtToken(token));
    }
}

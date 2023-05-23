package t6proj.authorization;

import org.springframework.stereotype.Service;
import t6proj.authorization.business.TokenAuthorizationHandler;
import t6proj.authorization.business.UserStorage;
import t6proj.user.dto.User;

@Service
public class AuthorizationService {
    private final UserStorage userStorage;
    private final TokenAuthorizationHandler tokenAuthorizationHandler;

    public AuthorizationService(
            UserStorage userStorage,
            TokenAuthorizationHandler tokenAuthorizationHandler
    ) {
        this.userStorage = userStorage;
        this.tokenAuthorizationHandler = tokenAuthorizationHandler;
    }

    public User getCurrentUser()
    {
        return this.userStorage.getCurrentUser();
    }

    public void handleAuthorizationRequest(String token)
    {
        this.tokenAuthorizationHandler.handle(token);
    }
}

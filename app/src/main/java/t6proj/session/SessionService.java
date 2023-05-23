package t6proj.session;

import adminlte.session.SessionServiceInterface;
import adminlte.session.dto.SessionData;
import org.springframework.stereotype.Service;
import t6proj.session.business.InMemorySessionStorage;

@Service
public class SessionService implements SessionServiceInterface {
    private final InMemorySessionStorage inMemorySessionStorage;

    public SessionService(InMemorySessionStorage inMemorySessionStorage)
    {
        this.inMemorySessionStorage = inMemorySessionStorage;
    }

    @Override
    public SessionData getUserSessionData(Integer userId) {
        return this.inMemorySessionStorage.getUserSessionData(userId);
    }

    @Override
    public void setUserSessionData(Integer userId, SessionData sessionData) {
        this.inMemorySessionStorage.setUserSessionData(userId, sessionData);
    }
}

package t6proj.session.business;

import adminlte.session.dto.SessionData;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InMemorySessionStorage {
    public Map<Integer, SessionData> sessionData = new HashMap<>();

    public SessionData getUserSessionData(Integer userId) {
        var sessionData = this.sessionData.get(userId);
        if (sessionData == null) {
            sessionData = new SessionData();
        }

        return sessionData;
    }

    public void setUserSessionData(Integer userId, SessionData sessionData) {
        this.sessionData.put(userId, sessionData);
    }
}

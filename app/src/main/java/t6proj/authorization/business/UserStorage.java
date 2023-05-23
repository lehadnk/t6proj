package t6proj.authorization.business;

import org.springframework.stereotype.Component;
import t6proj.user.dto.User;

@Component
public class UserStorage {
    public ThreadLocal<User> userStorage = new ThreadLocal<>();

    public User getCurrentUser()
    {
        return this.userStorage.get();
    }

    public void setCurrentUser(User user)
    {
        this.userStorage.set(user);
    }
}

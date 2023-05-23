package t6proj.user;

import org.springframework.stereotype.Service;
import t6proj.user.dto.User;
import t6proj.user.persistence.dao.UserDao;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(
            UserDao userDao
    ) {
        this.userDao = userDao;
    }

    public User getUserByEmail(String email)
    {
        return this.userDao.getUserByEmail(email);
    }
}

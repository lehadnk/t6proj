package t6proj.user;

import org.springframework.stereotype.Service;
import t6proj.framework.dto.PaginatedEntityList;
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

    public User getUserById(Integer id)
    {
        return this.userDao.getUserById(id);
    }

    public PaginatedEntityList<User> getUserList(Integer page, Integer pageSize)
    {
        return this.userDao.getUserList(page, pageSize);
    }

    public User saveUser(User user)
    {
        return this.userDao.saveUser(user);
    }

    public void deleteUser(Integer id) {
        this.userDao.deleteUser(id);
    }
}

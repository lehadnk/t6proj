package t6proj.user.persistence.dao;

import org.springframework.stereotype.Component;
import t6proj.user.dto.User;
import t6proj.user.persistence.mapper.UserMapper;
import t6proj.user.persistence.repository.UserRepository;

@Component
public class UserDao {
    private final UserRepository repository;
    private final UserMapper mapper = UserMapper.INSTANCE;

    public UserDao(
            UserRepository repository
    ) {
        this.repository = repository;
    }

    public User getUserByEmail(String email)
    {
        var entity = this.repository.getByEmail(email);
        return this.mapper.entityToDto(entity);
    }
}

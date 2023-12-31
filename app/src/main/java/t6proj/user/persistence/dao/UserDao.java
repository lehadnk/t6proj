package t6proj.user.persistence.dao;

import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import t6proj.framework.dto.PaginatedEntityList;
import t6proj.user.dto.User;
import t6proj.user.persistence.mapper.UserMapper;
import t6proj.user.persistence.repository.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

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

    public User getUserById(Integer id) {
        var entity = this.repository.getUserById(id);
        return this.mapper.entityToDto(entity);
    }

    @SneakyThrows
    public User saveUser(User user)
    {
        var entity = this.mapper.dtoToEntity(user);
        if (user.changePassword != null) {
            var digest = MessageDigest.getInstance("SHA-256");
            entity.password = new String(
                    Base64.getEncoder().encode(digest.digest(user.changePassword.getBytes(StandardCharsets.UTF_8))),
                    StandardCharsets.UTF_8
            );
        }

        this.repository.save(entity);
        user.id = entity.id;

        return user;
    }

    public PaginatedEntityList<User> getUserList(Integer page, Integer pageSize) {
        var pageable = PageRequest.of(page - 1, pageSize);
        var userEntityList = this.repository.getUserList(pageable);
        var usersCount = this.repository.count();

        var userList = new ArrayList<User>(userEntityList.size());
        for (var userEntity : userEntityList) {
            userList.add(this.mapper.entityToDto(userEntity));
        }

        return new PaginatedEntityList<>(
                userList,
                page,
                (int) Math.ceil((double) usersCount / pageSize)
        );
    }

    public void deleteUser(Integer id)
    {
        this.repository.deleteById(id);
    }
}

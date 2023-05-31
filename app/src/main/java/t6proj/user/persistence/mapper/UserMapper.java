package t6proj.user.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import t6proj.user.dto.User;
import t6proj.user.persistence.entity.UserEntity;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User entityToDto(UserEntity entity);

    UserEntity dtoToEntity(User user);
}

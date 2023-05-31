package t6proj.user.persistence.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import t6proj.user.persistence.entity.UserEntity;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query("SELECT n FROM UserEntity n WHERE n.email = :email")
    UserEntity getByEmail(String email);

    @Query("SELECT n FROM UserEntity n WHERE n.id = :id")
    UserEntity getUserById(Integer id);

    @Query("SELECT n FROM UserEntity n")
    List<UserEntity> getUserList(Pageable pageable);
}

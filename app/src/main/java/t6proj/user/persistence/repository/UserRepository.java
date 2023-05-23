package t6proj.user.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import t6proj.user.persistence.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query("SELECT n FROM UserEntity n WHERE n.email = :email")
    UserEntity getByEmail(String email);
}

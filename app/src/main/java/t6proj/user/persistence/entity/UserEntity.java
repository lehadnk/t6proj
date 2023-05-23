package t6proj.user.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ts_admin_id_seq")
    @SequenceGenerator(name = "ts_admin_id_seq", sequenceName = "ts_admin_id_seq", allocationSize = 1)
    @Column(name="id")
    public Integer id;

    @Column(name="email")
    public String email;

    @Column(name="password")
    public String password;
}

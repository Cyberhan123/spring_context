package cn.hselfweb.cnjpbbs.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user_role")
public class UserRole {
    @Version
    Long version;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    Long userRoleId;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "role_id")
    Long roleId;

    @Column(name = "user_role_status")
    String userRoleStatus;

}

package cn.hselfweb.cnjpbbs.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "role_permission")
public class RolePermission {
    @Version
    Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "user_role_id")
    Long rolePermissionId;

    @Column(name = "role_id")
    Long roleId;

    @Column(name = "permission_id")
    Long permissionId;

    @Column(name = "role_permission_status")
    String rolePermissionStatus;

}

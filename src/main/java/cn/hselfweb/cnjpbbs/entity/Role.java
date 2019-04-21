package cn.hselfweb.cnjpbbs.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "role")
public class Role {
    @Version
    Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "role_id")
    Long roleId;

    @Column(name = "role_name")
    String roleName;

    @Column(name = "role_status")
    String roleStatus;

}

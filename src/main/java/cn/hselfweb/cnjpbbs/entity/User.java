package cn.hselfweb.cnjpbbs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
    @Version
    Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long userId;

    @Column(name = "email")
    String email;

    @Column(name = "phone")
    String phone;

    @Column(name = "name")
    String name;

    @Column(name = "password")
    @JsonIgnore
    String password;

}

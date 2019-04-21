package cn.hselfweb.cnjpbbs.repository;

import cn.hselfweb.cnjpbbs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmailAndPassword(String e,String p);
    User findUserByNameAndAndPassword(String N,String p);
    User findUserByPhoneAndPassword(String p,String pa);

}

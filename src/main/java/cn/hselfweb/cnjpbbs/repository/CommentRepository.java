package cn.hselfweb.cnjpbbs.repository;

import cn.hselfweb.cnjpbbs.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}

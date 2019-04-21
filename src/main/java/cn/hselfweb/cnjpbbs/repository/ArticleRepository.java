package cn.hselfweb.cnjpbbs.repository;

import cn.hselfweb.cnjpbbs.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> findArticleById(Long id);
}

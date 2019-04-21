package cn.hselfweb.cnjpbbs.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "articles")
public class Article {
    @Version
    Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    Long id;

    @Column(name = "article_title")
    String title;

    @Column(name = "article_context")
    String context;

    @Column(name = "article_author")
    String author;

    @Column(name = "time")
    Date time;

}

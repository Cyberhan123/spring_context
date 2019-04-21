package cn.hselfweb.cnjpbbs.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "banner")
public class Banner {
    @Version
    Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_id")
    Long id;

    @Column(name = "banner_image")
    String bannerImg;

    @Column(name = "article_id")
    Long articleid;

    @Column(name = "grou")
    Long group;

}

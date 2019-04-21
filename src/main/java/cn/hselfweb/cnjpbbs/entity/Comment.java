package cn.hselfweb.cnjpbbs.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class Comment {
    @Version
    Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    Long commentId;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "comment_context")
    String commentContext;

    @Column(name = "comment_time")
    Date commentTime;
}

package com.kh.finalPlayTime.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Reply {
    @Id
    @Column(name = "reply_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //한 명의 멤버가 여러 댓글을 쓸 수 있으니 / 다수의 댓글을 한 명의 유저가 작성하니 ManyToOne
    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberInfo memberInfo;

    //한 개의 게시글에 여러 댓글이 달릴 수 있으니 다수의 댓글이 하나의 게시글을 참고 하니 ManyToOne
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(length = 300)
    private String replyContent;

    private LocalDateTime replyDate;
}

package com.kh.finalPlayTime.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberInfo memberInfo;

    @Column(length = 100, nullable = false)
    private String postTitle;
    @Lob
    @Column(nullable = false)
    private String postContent;
    @Column(length = 500)
    private String postImageUrl;
    @Column(length = 20)
    private String postCategory;
    private int postViews;
    private LocalDateTime postDate;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();
    // 하나의 게시글에 여러개의 댓글이 달릴 수 있으니 OneToMany
    // 댓글 엔터티 기준으로는 여러 개의 같은 게시글 코드가 들어오니 ManyToOne

}

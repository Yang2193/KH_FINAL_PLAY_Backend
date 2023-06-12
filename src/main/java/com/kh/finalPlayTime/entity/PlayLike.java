package com.kh.finalPlayTime.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class PlayLike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "play_like_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "play_id")
    private PlayInfo playInfo;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberInfo memberInfo;

}

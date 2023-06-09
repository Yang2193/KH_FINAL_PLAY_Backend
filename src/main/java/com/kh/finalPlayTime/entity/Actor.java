package com.kh.finalPlayTime.entity;

import javax.persistence.*;

@Entity
@Table(name = "actor")
public class Actor {
    @Id
    @GeneratedValue
    private Long actorId;
    @Column(length = 20 )
    private String actorName;
    @Column(length = 20 )
    private String  roleName;
    @Column(length = 500)
    private String actorImage;
    @ManyToOne
    @JoinColumn(name = "play_id")
    private PlayInfo playInfo;
}

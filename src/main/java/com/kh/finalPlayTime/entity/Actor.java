package com.kh.finalPlayTime.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "actor")
@Getter
@Setter
@ToString

public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

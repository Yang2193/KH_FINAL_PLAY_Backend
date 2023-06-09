package com.kh.finalPlayTime.entity;

import javax.persistence.*;

@Entity
@Table(name = "theater")
public class Theater {
    @Id
    @GeneratedValue
    private Long theaterId;
    @Column(length = 20)
    private String theaterName;
    @Column(length = 200)
    private String addr;
    @OneToOne
    @JoinColumn(name = "play_id")
    private PlayInfo playInfo;
}

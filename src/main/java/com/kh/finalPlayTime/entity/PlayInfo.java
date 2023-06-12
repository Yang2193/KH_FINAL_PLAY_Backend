package com.kh.finalPlayTime.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "play_info")
@Getter
@Setter
@ToString
public class PlayInfo {
    @Id
    @Column(name = "play_id_pk")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "play_id", length = 10, nullable = false, unique = true)
    private String playId;
    @Column(length = 50)
    private String title;
    @Column(length = 30)
    private String periodStart;
    @Column(length = 30)
    private String periodEnd;
    @Column(length = 30)
    private String playTime;
    @Column(length = 30)
    private String playAge;
    @Column(length = 500)
    private String infoImageUrl;
    @Column(length = 500)
    private String posterImageUrl;
    @Column(length = 500)
    private String noticeImageUrl;
    @Column(length = 30)
    private String theaterName;
}

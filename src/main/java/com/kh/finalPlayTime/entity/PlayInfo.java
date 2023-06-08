package com.kh.finalPlayTime.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "play_info")
public class PlayInfo {
    @Id
    @Column(length = 10)
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

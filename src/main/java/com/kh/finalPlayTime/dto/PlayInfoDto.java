package com.kh.finalPlayTime.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayInfoDto {
    private String playId;
    private String title;
    private String periodStart;
    private String periodEnd;
    private String playTime;
    private String playAge;
    private String infoImageUrl;
    private String posterImageUrl;
    private String noticeImageUrl;
    private String theaterName;
}

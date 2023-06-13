package com.kh.finalPlayTime.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayListDto {
    private String playId;
    private String title;
    private String periodStart;
    private String periodEnd;
    private String theaterName;
    private String imageUrl;
}

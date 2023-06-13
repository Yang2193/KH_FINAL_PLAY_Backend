package com.kh.finalPlayTime.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDto {
    private Long id;
    private String postTitle;
    private String postContent;
    private String postImageUrl;
    private String postCategory;
    private int postViews;
    private LocalDateTime postDate;
}

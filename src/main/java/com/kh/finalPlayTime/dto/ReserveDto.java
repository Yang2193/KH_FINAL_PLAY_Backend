package com.kh.finalPlayTime.dto;

import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReserveDto {
    private Long reserveId; // 예매 번호(PK)
    private String playId;
    private String userId;
    private LocalDateTime reserveDate; // 예매 일시
    private String reserveTime;// 공연 관람 시간
    private String seeDate; // 공연 관람일
    private String seatRating; // 좌석 등급
    private String seatNumbers; // 좌석 번호
}
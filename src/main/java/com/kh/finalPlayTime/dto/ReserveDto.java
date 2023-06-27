package com.kh.finalPlayTime.dto;

import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReserveDto {
    private Long reserveId; // 예매 번호(PK)
//    private int seatNum; // 좌석 번호
    private String playId;
    private String userId;
    private String reserveDate; // 예매 일시
    private String seeDate; // 공연 관람일
    private String seatPosition; // 예매한 좌석 위치

//    private Long paymentId;
}
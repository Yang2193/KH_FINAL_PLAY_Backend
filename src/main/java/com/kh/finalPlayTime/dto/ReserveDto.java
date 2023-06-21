package com.kh.finalPlayTime.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@NoArgsConstructor // 기본 생성자를 자동으로 만들어 줌
@AllArgsConstructor
public class ReserveDto {
    private Long reserveId; // 예매 번호(PK)
    private int seatNum; // 좌석 번호
    private String seatPosition; // 예매한 좌석 위치
    private LocalDateTime seeDate; // 공연 관람일
    private LocalDateTime reserveDate; // 예매 일시
    private String userId;
    private String playId;
    private Long paymentId;
}
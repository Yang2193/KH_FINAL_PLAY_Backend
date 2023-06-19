package com.kh.finalPlayTime.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reserveId;

    @ManyToOne
    @JoinColumn(name = "play_ID")
    private PlayInfo playInfo;

    @ManyToOne
    @JoinColumn(name = "userId")
    private MemberInfo memberInfo;

    @OneToOne // Payment 엔티티와 일대일 매핑
    @JoinColumn(name = "payment_id") // 결제번호(FK)
    private Payment payment;

    private LocalDateTime ticketDate; // 예매 일시
    private LocalDateTime seeDate; // 공연 관람일
    private int seatNum; // 좌석 번호
    private String seatPosition; // 예매한 좌석 위치

}

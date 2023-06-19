package com.kh.finalPlayTime.entity;

import com.kh.finalPlayTime.constant.PaymentStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId; // 결제번호(PK)

    @ManyToOne
    @JoinColumn(name = "userId")// 회원번호(FK)
    private MemberInfo memberInfo; // Member 객체

    private String paymentType; // 결제 수단
    private int paymentPrice; // 결제 가격
    private int paymentSale; // 할인 금액
    private int paymentTotal; // 최종 금액

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus; // 결제 상태(Enum)

    private LocalDateTime paymentDate; // 결제 일시
    private LocalDateTime refundDate; // 환불 일시
    private int paymentCnt; // 구매 수량
}

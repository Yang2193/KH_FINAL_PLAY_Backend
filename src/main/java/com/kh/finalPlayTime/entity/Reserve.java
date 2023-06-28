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
    @Column(name = "reserve_id")
    private Long id; // 예매아이디

    @ManyToOne
    @JoinColumn(name = "play_id")
    private PlayInfo playInfo; // 연극 정보

    @ManyToOne
    @JoinColumn(name = "user_id")
    private MemberInfo memberInfo; // 회원정보

    @Column(name = "reserve_date")
    private LocalDateTime reserveDate; // 예매 일시

    @Column(name = "see_date")
    private String seeDate; // 공연 관람일

    @Column(name = "seat_position")
    private String seatPosition; // 예매한 좌석 위치

}

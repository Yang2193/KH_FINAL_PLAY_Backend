package com.kh.finalPlayTime.entity;

import javax.persistence.*;

@Entity
@Table(name = "seat")

public class Seat {
    @Id
    @GeneratedValue
    private Long seatId;
    @ManyToOne
    @JoinColumn(name = "play_id")
    private PlayInfo playInfo;
    @Column(length = 10)
    private String seatRating;
    private Long price;
}

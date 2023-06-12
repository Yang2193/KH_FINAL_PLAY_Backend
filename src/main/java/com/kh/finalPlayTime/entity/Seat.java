package com.kh.finalPlayTime.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "seat")
@Getter
@Setter
@ToString

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

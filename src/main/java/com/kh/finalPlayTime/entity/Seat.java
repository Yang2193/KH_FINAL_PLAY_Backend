package com.kh.finalPlayTime.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Seat {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(length = 10)
    private String playId;
    @Column(length = 10)
    private String seatRating;
    @Column(length = 10)
    private int price;
}

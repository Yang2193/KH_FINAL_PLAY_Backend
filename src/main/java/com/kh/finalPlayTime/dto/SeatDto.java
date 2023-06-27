package com.kh.finalPlayTime.dto;

import com.kh.finalPlayTime.entity.SeatNumbers;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter@Setter@ToString
public class SeatDto {
    private Long seatId;
    private String theaterId;
    private String theaterName;
    private List<SeatNumbers> seatNumbers;

}

package com.kh.finalPlayTime.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theater")
@Getter
@Setter
@ToString

public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theaterId;
    @Column(length = 20)
    private String theaterName;
    @Column(length = 200)
    private String addr;
    @ManyToOne
    @JoinColumn(name = "play_id")
    private PlayInfo playInfo;
}

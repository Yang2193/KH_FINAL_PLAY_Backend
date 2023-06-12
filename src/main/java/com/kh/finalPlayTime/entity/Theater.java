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
    @GeneratedValue
    private Long theaterId;
    @Column(length = 20)
    private String theaterName;
    @Column(length = 200)
    private String addr;
    @OneToMany(mappedBy = "playId")
    private List<PlayInfo> playInfos = new ArrayList<>();
}

package com.kh.finalPlayTime.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter @ToString
public class MemberInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 20)
    private String memberId;
    @Column(length = 12)
    private String name;
    @Column(length = 20)
    private String nickname;
    @Column(length = 50)
    private String email;
    @Column(length = 20)
    private String phoneNum;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime joinDate;

}

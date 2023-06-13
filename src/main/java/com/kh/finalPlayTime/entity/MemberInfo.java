package com.kh.finalPlayTime.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Data
public class MemberInfo {
    @Id
    @Column(unique = true, length = 20, nullable = false)
    private String userId;
    @Column(name = "password", length = 20)
    private String userPw;
    @Column(length = 12)
    private String userName;

    @Column(length = 50)
    private String userEmail;
    @Column(length = 20)
    private String userPhone;
    @Column
    private LocalDateTime joinDate;

}

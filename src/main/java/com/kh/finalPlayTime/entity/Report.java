package com.kh.finalPlayTime.entity;

import com.kh.finalPlayTime.constant.ReportStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reportId;
    private Long userId;
    private Long postId;
    private Long commentId;
    @Column(length = 300)
    private String reportContent;
    private LocalDateTime reportDate;
    @Enumerated(EnumType.STRING)
    private ReportStatus reportStatus;

}

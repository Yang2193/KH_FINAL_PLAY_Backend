package com.kh.finalPlayTime.service;

import com.kh.finalPlayTime.constant.ReportStatus;
import com.kh.finalPlayTime.dto.ReportDto;
import com.kh.finalPlayTime.entity.Report;
import com.kh.finalPlayTime.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public void createReport(ReportDto reportDto) {
        Report report = new Report();
        report.setReportId(report.getReportId());
        report.setNickname(reportDto.getNickname());
        report.setPostId(reportDto.getPostId());
        report.setCommentId(reportDto.getCommentId());
        report.setReportContent(reportDto.getReportContent());
        report.setReportDate(LocalDateTime.now());
        report.setReportStatus(ReportStatus.PROCESS);
        reportRepository.save(report);
    }
}

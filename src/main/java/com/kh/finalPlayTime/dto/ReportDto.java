    package com.kh.finalPlayTime.dto;

    import com.kh.finalPlayTime.constant.ReportStatus;
    import lombok.Getter;
    import lombok.Setter;

    import java.time.LocalDateTime;
    @Getter
    @Setter
    public class ReportDto {
        private Long reportId;
        private Long userId;
        private Long postId;
        private Long commentId;
        private String reportContent;
        private LocalDateTime reportDate;
        private ReportStatus reportStatus;
    }

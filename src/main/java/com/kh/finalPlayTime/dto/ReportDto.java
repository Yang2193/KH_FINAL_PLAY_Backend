    package com.kh.finalPlayTime.dto;

    import com.kh.finalPlayTime.constant.ReportStatus;
    import lombok.Getter;
    import lombok.Setter;

    import java.time.LocalDateTime;
    @Getter
    @Setter
    public class ReportDto {
        private Long reportId; //신고 당한 사람 아이디 댓글 테이블에 userId 있음
        private Long userId; // 신고 한 사람의 아이디 로컬 스토리지에 있음
        private Long postId; //  게시글 번호
        private Long commentId; // 댓글 번호
        private String reportContent; // 신고 내용
        private LocalDateTime reportDate;
        private ReportStatus reportStatus;
    }

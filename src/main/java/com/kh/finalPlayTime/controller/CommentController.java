    package com.kh.finalPlayTime.controller;

    import com.kh.finalPlayTime.dto.CommentDto;
    import com.kh.finalPlayTime.service.CommentService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @CrossOrigin(origins = "http://localhost:3000")
    @RestController
    @RequestMapping("/comments")
    public class CommentController {

        private final CommentService commentService;

        @Autowired
        public CommentController(CommentService commentService) {
            this.commentService = commentService;
        }

        /**
         * 새로운 댓글 생성
         *
         * @param postId      게시물 ID
         * @param commentDto  댓글 DTO 객체
         * @return 생성된 댓글 DTO 객체와 HTTP 상태 코드
         */
        @PostMapping("/{postId}")
        public ResponseEntity<CommentDto> createComment(@PathVariable Long postId, @RequestBody CommentDto commentDto) {
            CommentDto createdComment = commentService.createComment(postId, commentDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
        }

        /**
         * 댓글 ID로 댓글 조회
         *
         * @param id  댓글 ID
         * @return 조회된 댓글 DTO 객체와 HTTP 상태 코드
         */
        @GetMapping("/{id}")
        public ResponseEntity<CommentDto> getCommentById(@PathVariable Long id) {
            CommentDto commentDto = commentService.getCommentById(id);
            if (commentDto != null) {
                return ResponseEntity.ok(commentDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        /**
         * 게시물 ID로 댓글 리스트 조회
         *
         * @param postId  게시물 ID
         * @return 조회된 댓글 DTO 리스트와 HTTP 상태 코드
         */
        @GetMapping("/detail/{postId}")
        public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable Long postId) {
            List<CommentDto> commentDtoList = commentService.getCommentsByPostId(postId);
            if (!commentDtoList.isEmpty()) {
                return ResponseEntity.ok(commentDtoList);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }

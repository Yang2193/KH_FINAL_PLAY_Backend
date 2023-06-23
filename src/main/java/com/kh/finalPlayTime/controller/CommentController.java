    package com.kh.finalPlayTime.controller;

    import com.kh.finalPlayTime.dto.CommentDto;
    import com.kh.finalPlayTime.service.CommentService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/comments")
    @RequiredArgsConstructor
    public class CommentController {

        private final CommentService commentService;


        @PostMapping("/createComment")
        public ResponseEntity<Boolean> createCom(@RequestBody CommentDto commentDto){
            boolean isSuccess = commentService.createComment(commentDto);
            return new ResponseEntity<>(isSuccess, HttpStatus.OK);
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
        @DeleteMapping("/{commentId}")
        public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
            try {
                commentService.deleteComment(commentId);
                return ResponseEntity.ok("댓글이 삭제되었습니다.");
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 삭제 중 오류가 발생했습니다.");
            }
        }
    }

    package com.kh.finalPlayTime.service;

    import com.kh.finalPlayTime.dto.CommentDto;
    import com.kh.finalPlayTime.entity.Comment;
    import com.kh.finalPlayTime.entity.Post;
    import com.kh.finalPlayTime.repository.CommentRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    @Service
    public class CommentService {

        private final CommentRepository commentRepository;

        @Autowired
        public CommentService(CommentRepository commentRepository) {
            this.commentRepository = commentRepository;
        }

        // 댓글 생성
        public CommentDto createComment(Long postId, CommentDto commentDto) {
            Comment comment = new Comment();
            comment.setCommentContent(commentDto.getCommentContent());
            comment.setCommentDate(LocalDateTime.now()); // 현재 시간 설정

            // postId 설정
            Post post = new Post();
            post.setId(postId);
            comment.setPost(post);

            Comment savedComment = commentRepository.save(comment);

            CommentDto createdCommentDto = new CommentDto();
            createdCommentDto.setId(savedComment.getId());
            createdCommentDto.setCommentContent(savedComment.getCommentContent());
            createdCommentDto.setCommentDate(savedComment.getCommentDate()); // 생성된 댓글의 시간 설정
            createdCommentDto.setPostId(savedComment.getPost().getId()); // postId 설정
            createdCommentDto.setUserId(commentDto.getUserId()); // userId 설정
            //여기는 유저 아이디 들어가야 할 부분

            return createdCommentDto;
        }
        // 댓글 수정
        public CommentDto updateComment(Long commentId, CommentDto commentDto) {
            Optional<Comment> commentOptional = commentRepository.findById(commentId);
            if (commentOptional.isPresent()) {
                Comment comment = commentOptional.get();
                comment.setCommentContent(commentDto.getCommentContent());

                Comment updatedComment = commentRepository.save(comment);

                CommentDto updatedCommentDto = new CommentDto();
                updatedCommentDto.setId(updatedComment.getId());
                updatedCommentDto.setCommentContent(updatedComment.getCommentContent());

                return updatedCommentDto;
            } else {
                // 해당 commentId에 해당하는 댓글이 없는 경우
                return null;
            }
        }

        // 댓글 삭제
        public void deleteComment(Long commentId) {
            commentRepository.deleteById(commentId);
        }

        // 댓글 ID로 댓글 조회
        public CommentDto getCommentById(Long commentId) {
            Optional<Comment> commentOptional = commentRepository.findById(commentId);
            if (commentOptional.isPresent()) {
                Comment comment = commentOptional.get();
                CommentDto commentDto = new CommentDto();
                commentDto.setId(comment.getId());
                commentDto.setCommentContent(comment.getCommentContent());
                // 필요한 다른 속성들도 CommentDto에 맞게 매핑해주세요.
                return commentDto;
            } else {
                return null;
            }
        }

        // 게시물 ID로 댓글 리스트 조회
        public List<CommentDto> getCommentsByPostId(Long postId) {
            List<Comment> comments = commentRepository.findByPostId(postId);
            List<CommentDto> commentDtoList = new ArrayList<>();
            CommentDto commentDto = null;
            for (Comment comment : comments) {
                commentDto = new CommentDto();
                commentDto.setId(comment.getId());
                commentDto.setCommentContent(comment.getCommentContent());
                // 필요한 다른 속성들도 CommentDto에 맞게 매핑해주세요.
                commentDtoList.add(commentDto);
            }
            return commentDtoList;
        }
    }




    ///////변경 ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ

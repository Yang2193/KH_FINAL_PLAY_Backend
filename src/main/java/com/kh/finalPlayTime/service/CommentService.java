    package com.kh.finalPlayTime.service;

    import com.kh.finalPlayTime.dto.CommentDto;
    import com.kh.finalPlayTime.dto.PostDto;
    import com.kh.finalPlayTime.entity.Comment;
    import com.kh.finalPlayTime.entity.MemberInfo;
    import com.kh.finalPlayTime.entity.Post;
    import com.kh.finalPlayTime.repository.CommentRepository;
    import com.kh.finalPlayTime.repository.PostRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.stereotype.Service;

    import javax.transaction.Transactional;
    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    @Service
    @Transactional
    @RequiredArgsConstructor
    public class CommentService {

        private final CommentRepository commentRepository;
        private final PostRepository postRepository;
        private final PostService postService;

        //댓글 생성
        public boolean createComment(CommentDto commentDto){
            Comment comment = new Comment();
            Post post = new Post();

            System.out.println(commentDto.getPostId());
            System.out.println(commentDto.getCommentContent());
            post.setId(commentDto.getPostId());
            comment.setPost(post);

            comment.setCommentDate(LocalDateTime.now());


            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setUserId(commentDto.getUserId());
            memberInfo.setUserNickname(commentDto.getNickname());
            comment.setMemberInfo(memberInfo);

            comment.setCommentContent(commentDto.getCommentContent());
            try {
                commentRepository.save(comment);
                System.out.println("댓글 저장 완료");
                return true; // 저장 성공 시 true 반환
            } catch (Exception e) {
                System.out.println("댓글 저장 실패");
                return false; // 저장 실패 시 false 반환
            }
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
        //댓글 삭제
        public boolean deleteComment(Long commentId) {
            try {
                commentRepository.deleteById(commentId);
                return true; // 삭제 성공
            } catch (Exception e) {
                return false; // 삭제 실패
            }
        }



        // 게시물 ID로 댓글 리스트 조회
        public List<CommentDto> getCommentsByPostId(Long postId) {
            List<Comment> comments = commentRepository.findByPostIdOrderByCommentDateDesc(postId);
            List<CommentDto> commentDtoList = new ArrayList<>();
            CommentDto commentDto = null;
            for (Comment comment : comments) {
                commentDto = new CommentDto();
                commentDto.setId(comment.getId());
                commentDto.setCommentContent(comment.getCommentContent());
                commentDto.setCommentDate(comment.getCommentDate());
                commentDto.setUserId(comment.getMemberInfo().getUserId());
                commentDto.setNickname(comment.getMemberInfo().getUserNickname()); // 닉네임 설정
                commentDtoList.add(commentDto);
            }
            return commentDtoList;
        }
    }




    ///////변경 ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ

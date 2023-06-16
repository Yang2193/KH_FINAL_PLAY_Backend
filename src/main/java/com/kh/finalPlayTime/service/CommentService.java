package com.kh.finalPlayTime.service;

import com.kh.finalPlayTime.dto.CommentDto;
import com.kh.finalPlayTime.entity.Comment;
import com.kh.finalPlayTime.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setCommentContent(commentDto.getCommentContent());
        // 필요한 다른 속성들도 CommentDto에 맞게 매핑해주세요.

        Comment savedComment = commentRepository.save(comment);

        CommentDto createdCommentDto = new CommentDto();
        createdCommentDto.setId(savedComment.getId());
        createdCommentDto.setCommentContent(savedComment.getCommentContent());
        // 필요한 다른 속성들도 CommentDto에 맞게 매핑해주세요.

        return createdCommentDto;
    }

    public CommentDto updateComment(Long commentId, CommentDto commentDto) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setCommentContent(commentDto.getCommentContent());
            // 필요한 다른 속성들도 CommentDto에 맞게 업데이트해주세요.

            Comment updatedComment = commentRepository.save(comment);

            CommentDto updatedCommentDto = new CommentDto();
            updatedCommentDto.setId(updatedComment.getId());
            updatedCommentDto.setCommentContent(updatedComment.getCommentContent());
            // 필요한 다른 속성들도 CommentDto에 맞게 업데이트해주세요.

            return updatedCommentDto;
        } else {
            // 해당 commentId에 해당하는 댓글이 없는 경우에 대한 처리를 해주세요.
            return null;
        }
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

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

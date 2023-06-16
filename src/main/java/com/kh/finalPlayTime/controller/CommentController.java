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

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {
        CommentDto createdComment = commentService.createComment(commentDto);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long id) {
        CommentDto commentDto = commentService.getCommentById(id);
        if (commentDto != null) {
            return ResponseEntity.ok(commentDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable Long postId) {


        List<CommentDto> commentDtoList = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(commentDtoList);
    }

}

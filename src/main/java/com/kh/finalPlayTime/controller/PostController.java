package com.kh.finalPlayTime.controller;

import com.kh.finalPlayTime.dto.PostDto;
import com.kh.finalPlayTime.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//변경
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/select")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> postList = postService.getAllPosts();
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("/select/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) {
        PostDto post = postService.getPostById(postId);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{postId}/increase-views")
    public void increasePostViews(@PathVariable Long postId) {
        postService.increasePostViews(postId);
    }

    @PostMapping("/postUpload")
    public ResponseEntity<PostDto> addPost(@RequestBody PostDto postDto) {
        PostDto savedPost = postService.addPost(postDto);
        return ResponseEntity.ok(savedPost);
    }
}
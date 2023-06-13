package com.kh.finalPlayTime.controller;

import com.kh.finalPlayTime.dto.PostDto;
import com.kh.finalPlayTime.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시물 목록 조회
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> postList = postService.getAllPosts();
        return ResponseEntity.ok(postList);
    }
}

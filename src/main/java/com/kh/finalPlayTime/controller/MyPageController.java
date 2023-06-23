package com.kh.finalPlayTime.controller;

import com.kh.finalPlayTime.dto.PostDto;
import com.kh.finalPlayTime.service.MemberService;
import com.kh.finalPlayTime.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {
    @Autowired
    private PostService postService;
    public MyPageController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/find/post")
    public ResponseEntity<List<PostDto>> getMemberPost(@RequestBody Map<String, String> getMemberPostData) {
        String userId = getMemberPostData.get("userId");
        List<PostDto> memberPosts = postService.getMemberPosts(userId);
        if (memberPosts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        System.out.println(memberPosts);
        return ResponseEntity.ok(memberPosts);
    }
}

package com.kh.finalPlayTime.controller;

import com.kh.finalPlayTime.dto.CommentDto;
import com.kh.finalPlayTime.dto.PostDto;
import com.kh.finalPlayTime.service.AuthService;
import com.kh.finalPlayTime.service.CommentService;
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
    @Autowired
    private MemberService memberService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private AuthService authService;


    public MyPageController(MemberService memberService) {
        this.memberService = memberService;
    }
    public MyPageController(PostService postService) {
        this.postService = postService;
    }
    public MyPageController(CommentService commentService) { this.commentService = commentService; }

    @PostMapping("/post")
    public ResponseEntity<List<PostDto>> getMemberPost(@RequestBody Map<String, String> getMemberPostData) {
        String userId = getMemberPostData.get("userId");
        List<PostDto> memberPosts = postService.getMemberPosts(userId);
        if (memberPosts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(memberPosts);
    }

    @PostMapping("/comment")
    public ResponseEntity<List<CommentDto>> getMemberComment(@RequestBody Map<String, String> getMemberCommentData) {
        String userId = getMemberCommentData.get("userId");
        System.out.println(userId);
        List<CommentDto> memberComments = commentService.getMemberCommentUserId(userId);
        if(memberComments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(memberComments);
    }

    @PostMapping("/edit")
    public ResponseEntity<Boolean> updateMemberInfo(@RequestBody Map<String, String> updateData) {
        String userId = updateData.get("userId");
        String userPw = updateData.get("userPw");
        String userNickname = updateData.get("userNickname");
        String userName = updateData.get("userName");
        String userPhone = updateData.get("userPhone");
        String userEmail = updateData.get("userEmail");
        System.out.println("컨트롤러: " + userId + userPw + userNickname + userName + userPhone + userEmail);
        return ResponseEntity.ok(memberService.updateMemberInfo(userId,userPw,userNickname,userName,userPhone,userEmail));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Boolean> withDrawalUser(@RequestBody Map<String, String> deleteData) {
        String userId = deleteData.get("userId");
        String userPw = deleteData.get("userPw");
        System.out.println(userId + " " + userPw);
        return ResponseEntity.ok(authService.withdrawal(userId));
    }
}

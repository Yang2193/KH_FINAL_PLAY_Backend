package com.kh.finalPlayTime.controller;

import com.kh.finalPlayTime.dto.MemberDto;
import com.kh.finalPlayTime.dto.PostDto;
import com.kh.finalPlayTime.service.AdminService;
import com.kh.finalPlayTime.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/admin")
@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final PostService postService;
    @GetMapping("")
    public String adminMainPage(){
        return "admin/main";
    }

    // 회원 관리 컨트롤러 시작
    @GetMapping("/member")
    public String adminMemberPage(){
        return "admin/member";
    }

    @GetMapping("/getAllMember")
    public String adminGetAllMember(@RequestParam(required = false) String userId, Model model){
        List<MemberDto> list;

        if (userId != null) {
            // userId가 제공된 경우, 해당 회원만 필터링
            list = adminService.getMemberList().stream()
                    .filter(member -> member.getUserId().equals(userId))
                    .collect(Collectors.toList());
        } else {
            // userId가 제공되지 않은 경우, 모든 회원 조회
            list = adminService.getMemberList();
        }

        model.addAttribute("list", list);
        return "admin/member/member";
    }

    @GetMapping("/memberDetail")
    public String adminMemberDetail(@RequestParam String userId, Model model){
        MemberDto dto = adminService.getMember(userId);
        model.addAttribute("dto", dto);
        return "admin/member/memberDetail";
    }

    // 게시판 관리 컨트롤러 시작

    @GetMapping("/post")
    public String adminPost(@RequestParam(required = false) String title, Model model){
        List<PostDto> postList = postService.getAllPosts();
        model.addAttribute("list", postList);
        return "admin/post/post";
    }

    @GetMapping("/postDetail")
    public String adminPostDetail(@RequestParam Long postId, Model model){
        PostDto postDto = postService.getPostById(postId);
        model.addAttribute("dto", postDto);
        return "admin/post/postDetail";
    }




}

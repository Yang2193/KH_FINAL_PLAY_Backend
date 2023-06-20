package com.kh.finalPlayTime.controller;

import com.kh.finalPlayTime.dto.MemberDto;
import com.kh.finalPlayTime.dto.TokenDto;
import com.kh.finalPlayTime.jwt.TokenProvider;
import com.kh.finalPlayTime.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController { // 마이페이지 전용 컨트롤러로 활용.
    @Autowired
    private MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/find/id")
    public ResponseEntity<String> findMemberId(@RequestBody Map<String, String> findIdData) {
        String userName = findIdData.get("userName");
        String userEmail = findIdData.get("userEmail");
        System.out.println(userName + " " + userEmail);
        String memberDto = memberService.findId(userName, userEmail);
        if (memberDto == null) {
            // 아이디를 찾지 못한 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        // 아이디를 찾은 경우
        return ResponseEntity.ok(memberDto);
    }

    @PostMapping("/find/pw")
    public ResponseEntity<String> findMemberPw(@RequestBody Map<String, String> findPwData) {
        String userId = findPwData.get("userId");
        String userName = findPwData.get("userName");
        String userEmail = findPwData.get("userEmail");
        System.out.println(userId + " " + userName + " " + userEmail);
        String memberDto = memberService.findPw(userId, userName, userEmail);
        if (memberDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(memberDto);
    }

}

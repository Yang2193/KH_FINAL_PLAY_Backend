package com.kh.finalPlayTime.controller;

import com.kh.finalPlayTime.dto.MemberDto;
import com.kh.finalPlayTime.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    @Autowired
    private MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Boolean> memberLogin(@RequestBody Map<String, String> loginData) {
        String getUserId = loginData.get("userId");
        String getUserPw = loginData.get("userPw");
        boolean result = memberService.getLoginCheck(getUserId, getUserPw);
        if(result) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(true, HttpStatus.BAD_REQUEST);
        }
    }

    // 회원가입
    @PostMapping("/join/step2")
    public ResponseEntity<Map<String,String>> memberJoin(@RequestBody Map<String, String> joinData) {
        String getUserId = joinData.get("userId");
        String getUserPw = joinData.get("userPw");
        String getUserName = joinData.get("userName");
        String getUserPhone = joinData.get("userPhone");
        String getUserEmail = joinData.get("userEmail");
        boolean result = memberService.regMember(getUserId, getUserPw, getUserName, getUserPhone, getUserEmail);
        if(result) {
            return new ResponseEntity(true, HttpStatus.OK);
        } else {
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/find")
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

    @PostMapping("/find")
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

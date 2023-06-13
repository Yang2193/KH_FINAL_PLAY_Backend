package com.kh.finalPlayTime.controller;

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
}

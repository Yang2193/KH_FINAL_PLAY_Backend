package com.kh.finalPlayTime.controller;


import com.kh.finalPlayTime.jwt.JwtProvider;
import com.kh.finalPlayTime.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor

public class JwtController {
    private final JwtProvider jwtProvider;
    public String tokenCreate(String userId) throws Exception {
        String token = jwtProvider.createToken(userId);
        return token;
    }
}

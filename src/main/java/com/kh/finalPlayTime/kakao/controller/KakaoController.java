package com.kh.finalPlayTime.kakao.controller;

import com.kh.finalPlayTime.dto.TokenDto;
import com.kh.finalPlayTime.kakao.dto.KakaoProfile;
import com.kh.finalPlayTime.kakao.dto.KakaoTokens;
import com.kh.finalPlayTime.kakao.dto.TotalDto;
import com.kh.finalPlayTime.kakao.jwt.AuthTokens;
import com.kh.finalPlayTime.kakao.service.KAuthService;
import com.kh.finalPlayTime.kakao.service.KakaoProfileService;
import com.kh.finalPlayTime.kakao.service.OAuthTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
@Transactional
@Slf4j
public class KakaoController {
    private final OAuthTokenService oAuthTokenService;
    private final KakaoProfileService kakaoProfileService;
    private final KAuthService kAuthService;

    @PostMapping(value = "/kakao/callback")
    public TotalDto kakaoCallback(@RequestBody Map<String, String> tokenData) {
        String code = tokenData.get("code");
        KakaoTokens kakaoTokens = oAuthTokenService.getKakaoTokens(code);
        KakaoProfile kakaoProfile = kakaoProfileService.getKakaoProfile(kakaoTokens.getAccessToken());
        AuthTokens authTokens = kAuthService.login(kakaoProfile);
        return new TotalDto(kakaoProfile, authTokens);
    }

//    @GetMapping(value = "/kakao/callback")
//    public ResponseEntity<TokenDto> kakaoCallback(@RequestParam("code") String code) {
//        TokenDto tokenDto = kakaoAuthService.processKakaoCallback(code);
//        System.out.println(tokenDto);
//        return ResponseEntity.ok(tokenDto);
//    }
}
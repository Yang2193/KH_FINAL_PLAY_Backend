package com.kh.finalPlayTime.controller;

import antlr.Token;
import com.kh.finalPlayTime.dto.MemberDto;
import com.kh.finalPlayTime.dto.TokenDto;
import com.kh.finalPlayTime.service.AuthService;
import com.kh.finalPlayTime.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController { // 로그인 회원가입 ID/PW 찾기 여기에서
    private final AuthService authService;
    private final TokenService tokenService;

    @PostMapping("/signup")
    public ResponseEntity<MemberDto> signup(@RequestBody MemberDto memberDto){
        return ResponseEntity.ok(authService.signup(memberDto));
    }

    @PostMapping("/login") // 로그인시 ID, PW 일치 시 TRUE와 토큰을 함께 반환
    public ResponseEntity<TokenDto> getToken(@RequestBody MemberDto memberDto) {
        TokenDto tokenDto = authService.login(memberDto);
        return ResponseEntity.ok(tokenDto);
    }

    // AccessToken 재발급 코드
    @PostMapping("/token")
    public ResponseEntity<TokenDto> renewAccessToken(@RequestBody TokenDto requestDto){
        TokenDto renewDto = tokenService.createNewAccessToken(requestDto.getRefreshToken());
        return ResponseEntity.ok(renewDto);
    }

    @PostMapping("/loginTest")
    public ResponseEntity<Boolean> memberLogin(HttpServletResponse response, @RequestBody Map<String, String> loginData) throws Exception {
        String id = loginData.get("userId");
        String pwd = loginData.get("userPw");
        if((boolean)authService.loginService(id, pwd).get("login")) {
            log.info("로그인 성공해서 토큰 발급");
            String token = (String)authService.loginService(id, pwd).get("token");
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60*60);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie); // 응답에 쿠키 추가
            return ResponseEntity.ok(true);
        } else return ResponseEntity.ok(false);
    }
}

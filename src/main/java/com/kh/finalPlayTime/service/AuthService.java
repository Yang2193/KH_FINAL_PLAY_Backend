package com.kh.finalPlayTime.service;

import antlr.Token;
import com.kh.finalPlayTime.constant.Authority;
import com.kh.finalPlayTime.controller.JwtController;
import com.kh.finalPlayTime.dto.MemberDto;
import com.kh.finalPlayTime.dto.TokenDto;
import com.kh.finalPlayTime.entity.MemberInfo;
import com.kh.finalPlayTime.jwt.TokenProvider;
import com.kh.finalPlayTime.repository.MemberInfoRepository;
import com.kh.finalPlayTime.utils.TokenExpiredException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final AuthenticationManagerBuilder managerBuilder;
    private final MemberInfoRepository memberInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final JwtController jwtController;

    public MemberDto signup(MemberDto memberDto){
        if(memberInfoRepository.existsByUserId(memberDto.getUserId())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        MemberInfo memberInfo = memberDto.toMember(passwordEncoder);
        return MemberDto.of(memberInfoRepository.save(memberInfo));
    }

    public TokenDto login(MemberDto memberDto){
        UsernamePasswordAuthenticationToken authenticationToken = memberDto.toAuthentication();

        MemberInfo loginMember = memberInfoRepository.findByUserId(memberDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. 회원가입 진행 후 다시 시도해주세요."));

        // 비밀번호 맞는지 확인
        if (!passwordEncoder.matches(memberDto.getUserPw(), loginMember.getUserPw())) {
            throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
        }

        // 권한 확인
        if (loginMember.getAuthority().equals(Authority.ROLE_ADMIN)) {
            try {
                Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
                TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
                tokenDto.setAuthority(String.valueOf(Authority.ROLE_ADMIN));
                return tokenDto;
            } catch (AuthenticationException e) {
                throw e;
            }
        } else if (loginMember.getAuthority().equals(Authority.ROLE_USER)) {
            try {
                Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
                TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
                tokenDto.setAuthority(String.valueOf(Authority.ROLE_USER));
                return tokenDto;
            } catch (AuthenticationException e) {
                throw e;
            }
        } else {
            throw new IllegalArgumentException("권한이 올바르지 않습니다.");
        }
    }

    //토큰 검증 및 사용자 정보 추출
    public MemberInfo validateTokenAndGetUser(HttpServletRequest request, UserDetails userDetails){
        //토큰 추출
        String accessToken = request.getHeader("Authorization");
        if (accessToken != null && accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }
        // 🔑토큰 유효한지 검증
//        if (accessToken != null && tokenProvider.validateToken(accessToken)) {
        if (accessToken != null && tokenProvider.validateToken(accessToken)) {
            String userId = userDetails.getUsername();
            MemberInfo member = memberInfoRepository.findByUserId(userId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
            return member;
        } else {
            throw new TokenExpiredException("토큰이 만료됐습니다. Refresh Token 재발급이 필요합니다.");
        }
    }
// 20230623 jwt 쿠키 테스트
public Map<String, Object> loginService(String id, String pwd) throws Exception {
    Map<String, Object> map = new HashMap<>();
    Optional<MemberInfo> loginMember = memberInfoRepository.findByUserId(id);
    System.out.println(loginMember);
    if (loginMember.isEmpty()) {
        map.put("login", false);
    } else {
        if (!passwordEncoder.matches(pwd, loginMember.get().getUserPw())) {
            map.put("login", false);
        } else {
            if (loginMember.get().getAuthority() == Authority.ROLE_USER) {
                String accesstoken = jwtController.accessTokenCreate(loginMember.get().getUserId().toString());
                map.put("accessToken", accesstoken);
                String refreshtoken = jwtController.refreshTokenCreate(loginMember.get().getUserId().toString());
                map.put("refreshToken", refreshtoken);
                map.put("login", true);
            } else {
                map.put("login", false);
            }
        }
    }
    return map;
}
}

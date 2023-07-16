package com.kh.finalPlayTime.kakao.service;

import com.kh.finalPlayTime.constant.Authority;
import com.kh.finalPlayTime.dto.TokenDto;
import com.kh.finalPlayTime.jwt.TokenProvider;
import com.kh.finalPlayTime.kakao.dto.KakaoProfile;
import com.kh.finalPlayTime.kakao.entity.SocialMember;
import com.kh.finalPlayTime.kakao.jwt.AuthTokens;
import com.kh.finalPlayTime.kakao.jwt.AuthTokensGenerator;
import com.kh.finalPlayTime.kakao.jwt.KakaoTokenProvider;
import com.kh.finalPlayTime.kakao.repository.SocialMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class KAuthService {
    private final SocialMemberRepository socialMemberRepository;
    private final AuthenticationManagerBuilder managerBuilder;
    private final AuthTokensGenerator authTokensGenerator;
    private final PasswordEncoder passwordEncoder;

    public AuthTokens login(KakaoProfile kakaoProfile) {
        UsernamePasswordAuthenticationToken authenticationToken = kakaoProfile.toAuthentication();
        System.out.println(kakaoProfile.getPassword());
        SocialMember socialMember = kakaoProfile.toKakaoProfile(passwordEncoder);
        socialMemberRepository.save(socialMember);
        SocialMember loginMember = socialMemberRepository.findByIdAndPassword(kakaoProfile.getId(), kakaoProfile.getPassword())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자 회원 아님"));
        if (loginMember.getAuth().equals(Authority.ROLE_USER)) {
            try {
                Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
                AuthTokens authTokens = authTokensGenerator.generate(kakaoProfile.getId());
//                tokenDto.setAuthority(String.valueOf(Authority.ROLE_ADMIN));
                return authTokens;
            } catch (AuthenticationException e) {
                throw e;
            }
        } else {
            throw new IllegalArgumentException("권한이 올바르지 않습니다.");
        }
    }
}

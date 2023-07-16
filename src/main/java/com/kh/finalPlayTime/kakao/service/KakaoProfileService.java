package com.kh.finalPlayTime.kakao.service;

import com.kh.finalPlayTime.constant.Authority;
import com.kh.finalPlayTime.kakao.constant.SocialOAuth;
import com.kh.finalPlayTime.kakao.dto.KakaoProfile;

import com.kh.finalPlayTime.kakao.entity.SocialMember;
import com.kh.finalPlayTime.kakao.repository.SocialMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Component
@Transactional
public class KakaoProfileService {
    private final RestTemplate restTemplate;
    private final PasswordEncoder passwordEncoder;
    private final SocialMemberRepository socialMemberRepository;
    @Value("${cos.key}")
    private String cosKey;
    public KakaoProfile getKakaoProfile(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);
        ResponseEntity<KakaoProfile> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                kakaoProfileRequest,
                KakaoProfile.class
        );
        SocialMember socialMember = new SocialMember();
        socialMember.setId(response.getBody().getId());
        String encodeCosKey = passwordEncoder.encode(cosKey);
        socialMember.setPassword(encodeCosKey);
        socialMember.setEmail(response.getBody().getKakao_account().getEmail());
        socialMember.setNickname(response.getBody().getKakao_account().getProfile().getNickname());
        socialMember.setSocialOauth(SocialOAuth.KAKAO);
        socialMember.setAuth(Authority.ROLE_USER);
        socialMemberRepository.save(socialMember);
        System.out.println("저장 완료");
        return response.getBody();
    }
}
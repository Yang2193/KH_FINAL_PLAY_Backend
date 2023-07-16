package com.kh.finalPlayTime.kakao.dto;

import com.kh.finalPlayTime.dto.TokenDto;
import com.kh.finalPlayTime.kakao.dto.KakaoProfile;
import com.kh.finalPlayTime.kakao.jwt.AuthTokens;

public class TotalDto {
    private KakaoProfile kakaoProfile;
    private TokenDto tokenDto;
    private AuthTokens authTokens;

    public TotalDto(KakaoProfile kakaoProfile, AuthTokens authTokens) {
        this.kakaoProfile = kakaoProfile;
        this.authTokens = authTokens;
    }

    public KakaoProfile getKakaoProfile() {
        return kakaoProfile;
    }

    public AuthTokens getTokenDto() {
        return authTokens;
    }
}

package com.kh.finalPlayTime.kakao.dto;

import com.kh.finalPlayTime.dto.TokenDto;

public class TotalDto {
    private KakaoProfile kakaoProfile;
    private TokenDto tokenDto;

    public TotalDto(KakaoProfile kakaoProfile, TokenDto tokenDto) {
        this.kakaoProfile = kakaoProfile;
        this.tokenDto = tokenDto;
    }
    public KakaoProfile getKakaoProfile() {
        return kakaoProfile;
    }
    public TokenDto getTokenDto() {
        return tokenDto;
    }
}

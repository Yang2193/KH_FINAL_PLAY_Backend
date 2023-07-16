package com.kh.finalPlayTime.kakao.dto;

import com.kh.finalPlayTime.constant.Authority;
import com.kh.finalPlayTime.constant.Withdraw;
import com.kh.finalPlayTime.dto.MemberDto;
import com.kh.finalPlayTime.entity.MemberInfo;
import com.kh.finalPlayTime.kakao.constant.SocialOAuth;
import com.kh.finalPlayTime.kakao.entity.SocialMember;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@Setter


public class KakaoProfile {

    public Long id;
    public String password;
    public String connected_at;
    public Properties properties;
    public KakaoAccount kakao_account;

    @Getter
    @Setter
    public class Properties {
        public String nickname;
    }

    @Getter
    @Setter
    public class KakaoAccount {
        public Boolean profile_nickname_needs_agreement;
        public Profile profile;
        public Boolean has_email;
        public Boolean email_needs_agreement;
        public Boolean is_email_valid;
        public Boolean is_email_verified;
        public String email;

        @Getter
        @Setter
        public class Profile {
            public String nickname;

        }
    }
    public SocialMember toKakaoProfile(PasswordEncoder passwordEncoder) {

        return SocialMember.builder()
                .id(id)
                .password(passwordEncoder.encode(password))
                .nickname(getKakao_account().getProfile().nickname)
                .email(getKakao_account().email)
                .auth(Authority.ROLE_USER)
                .socialOauth(SocialOAuth.KAKAO)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(id, password);
    }
    public static MemberDto of(MemberInfo member) {
        return MemberDto.builder()
                .message(member.getUserId() + "님의 회원가입을 환영합니다.")
                .build();
    }
}
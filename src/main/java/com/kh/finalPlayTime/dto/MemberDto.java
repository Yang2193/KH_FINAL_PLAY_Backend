package com.kh.finalPlayTime.dto;

import com.kh.finalPlayTime.constant.Authority;
import com.kh.finalPlayTime.entity.MemberInfo;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private String userId;
    private String userPw;
    private String userName;
    private String userPhone;
    private String userEmail;
    private String nickname;

    private String message;

    public MemberInfo toMember(PasswordEncoder passwordEncoder) {
        return MemberInfo.builder()
                .user(userId)
                .email(userEmail)
                .password(passwordEncoder.encode(userPw))
                .name(userName)
                .nickname(nickname)
                .authority(Authority.ROLE_USER)
                .build();
    }
    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(userId, userPw);
    }

    public static MemberDto of(MemberInfo member) {
        return MemberDto.builder()
                .message(member.getUserId() + "님의 회원가입을 환영합니다.")
                .build();
    }
}

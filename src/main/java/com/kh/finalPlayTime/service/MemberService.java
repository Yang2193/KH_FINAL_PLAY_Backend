package com.kh.finalPlayTime.service;

import com.kh.finalPlayTime.dto.MemberDto;
import com.kh.finalPlayTime.dto.TokenDto;
import com.kh.finalPlayTime.entity.MemberInfo;
import com.kh.finalPlayTime.jwt.TokenProvider;
import com.kh.finalPlayTime.repository.MemberInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {
    private final AuthenticationManagerBuilder managerBuilder;
    private final MemberInfoRepository memberInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;


    // 아이디 찾기
    public String findId(String userName, String userEmail) {
        MemberInfo member = memberInfoRepository.findByUserNameAndUserEmail(userName, userEmail);
        if (member == null) {
            System.out.println("아이디를 찾지 못함");
            return null; // 아이디를 찾지 못한 경우 null을 반환하거나 원하는 대응을 수행
        }
        MemberDto memberDto = new MemberDto();
        memberDto.setUserId(member.getUserId());
        System.out.println("Test :" + memberDto.getUserId());
        String result = member.getUserId();
        return result;
    }

    // 패스워드 찾기
    public String findPw(String userId, String userName, String userEmail) {
        MemberInfo member = memberInfoRepository.findByUserIdAndUserNameAndUserEmail(userId, userName, userEmail);
        if (member == null) {
            System.out.println("아이디를 찾지 못함");
            return null; // 아이디를 찾지 못한 경우 null을 반환하거나 원하는 대응을 수행
        }
        MemberDto memberDto = new MemberDto();
        memberDto.setUserPw(member.getUserPw());
        System.out.println("Test :" + memberDto.getUserPw());
        String result = member.getUserPw();
        return result;
    }

    public MemberDto getMemberList(String userId) {
        Optional<MemberInfo> optionalMemberInfo = memberInfoRepository.findByUserId(userId);
        MemberDto memberDto = new MemberDto();
        if(optionalMemberInfo.isPresent()) {
            MemberInfo memberInfo = optionalMemberInfo.get();
            memberDto.setUserId(memberInfo.getUserId());
            memberDto.setUserPw(memberInfo.getUserPw());
            memberDto.setUserName(memberInfo.getUserName());
            memberDto.setUserPhone(memberInfo.getUserPhone());
            memberDto.setUserEmail(memberInfo.getUserEmail());
            memberDto.setMessage("조회 성공");
        } else{
            memberDto.setMessage("아이디가 존재하지 않습니다.");
        }
        return memberDto;
    }

}

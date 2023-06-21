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

    // 아이디로 회원 조회
//    public List<MemberDto> getMemberList(String userId) {
//        List<MemberDto> memberDtoList = new ArrayList<>();
//        List<MemberInfo> memberInfoList = memberInfoRepository.findByUserId(userId);
//        for(MemberInfo info : memberInfoList) {
//            MemberDto memberDto = new MemberDto();
//            memberDto.setUserId(info.getUserId());
//            memberDto.setUserPw(info.getUserPw());
//            memberDto.setUserName(info.getUserName());
//            memberDto.setUserPhone(info.getUserPhone());
//            memberDto.setUserEmail(info.getUserEmail());
//            memberDtoList.add(memberDto);
//        }
//        return memberDtoList;
//    }

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

//    // 로그인
//    public boolean getLoginCheck(String userId, String userPw) {
//        List<MemberInfo> memberInfoList = memberInfoRepository.findByUserIdAndUserPw(userId, userPw);
//        for(MemberInfo info : memberInfoList) {
//            return true;
//        }
//        return false;
//    }
//
//    // 회원가입
//    public boolean regMember(String userId, String userPwd, String userName, String userPhone, String userEmail) {
//        MemberInfo memberInfo = new MemberInfo();
//        memberInfo.setUserId(userId);
//        memberInfo.setUserPw(userPwd);
//        memberInfo.setUserName(userName);
//        memberInfo.setUserPhone(userPhone);
//        memberInfo.setUserEmail(userEmail);
//        memberInfo.setJoinDate(LocalDateTime.now());
//        MemberInfo rst = memberInfoRepository.save(memberInfo);
//        log.warn(rst.toString());
//        return true;
//    }

    //Token 적용 새 회원가입 메소드
    public MemberDto signup(MemberDto memberDto){
        if(memberInfoRepository.existsByUserId(memberDto.getUserId())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        MemberInfo memberInfo = memberDto.toMember(passwordEncoder);
        return MemberDto.of(memberInfoRepository.save(memberInfo));
    }

    // 로그인 시 토큰 발급
    public TokenDto login(MemberDto memberDto){
        UsernamePasswordAuthenticationToken authenticationToken = memberDto.toAuthentication();

        Authentication authentication = null;
        try {
            authentication = managerBuilder.getObject().authenticate(authenticationToken);
            System.out.println("인증 된거냐?");
            return tokenProvider.generateTokenDto(authentication);

        } catch (Exception e) {
            System.out.println("인증 실패: " + e.getMessage());
            e.printStackTrace();
            return tokenProvider.generateTokenDto(authentication);
        }
    }
}

package com.kh.finalPlayTime.service;

import com.kh.finalPlayTime.dto.MemberDto;
import com.kh.finalPlayTime.entity.MemberInfo;
import com.kh.finalPlayTime.repository.MemberInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {
    @Autowired
    private MemberInfoRepository memberInfoRepository;
    public MemberService(MemberInfoRepository memberInfoRepository) {
        this.memberInfoRepository = memberInfoRepository;
    }

    // 아이디로 회원 조회
    public List<MemberDto> getMemberList(String userId) {
        List<MemberDto> memberDtoList = new ArrayList<>();
        List<MemberInfo> memberInfoList = memberInfoRepository.findByUserId(userId);
        for(MemberInfo info : memberInfoList) {
            MemberDto memberDto = new MemberDto();
            memberDto.setUserId(info.getUserId());
            memberDto.setUserPw(info.getUserPw());
            memberDto.setUserName(info.getUserName());
            memberDto.setUserPhone(info.getUserPhone());
            memberDto.setUserEmail(info.getUserEmail());
            memberDtoList.add(memberDto);
        }
        return memberDtoList;
    }

    // 로그인
    public boolean getLoginCheck(String userId, String userPw) {
        List<MemberInfo> memberInfoList = memberInfoRepository.findByUserIdAndUserPw(userId, userPw);
        for(MemberInfo info : memberInfoList) {
            return true;
        }
        return false;
    }

    // 회원가입
    public boolean regMember(String userId, String userPwd, String userName, String userPhone, String userEmail) {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setUserId(userId);
        memberInfo.setUserPw(userPwd);
        memberInfo.setUserName(userName);
        memberInfo.setUserPhone(userPhone);
        memberInfo.setUserEmail(userEmail);
        memberInfo.setJoinDate(LocalDateTime.now());
        MemberInfo rst = memberInfoRepository.save(memberInfo);
        log.warn(rst.toString());
        return true;
    }
}

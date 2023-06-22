package com.kh.finalPlayTime.service;

import com.kh.finalPlayTime.constant.Authority;
import com.kh.finalPlayTime.dto.MemberDto;
import com.kh.finalPlayTime.dto.PostDto;
import com.kh.finalPlayTime.entity.MemberInfo;
import com.kh.finalPlayTime.entity.Post;
import com.kh.finalPlayTime.jwt.TokenProvider;
import com.kh.finalPlayTime.repository.CommentRepository;
import com.kh.finalPlayTime.repository.MemberInfoRepository;
import com.kh.finalPlayTime.repository.PostRepository;
import com.kh.finalPlayTime.repository.TheaterRepository;
import com.kh.finalPlayTime.utils.TokenExpiredException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AdminService {
    private final MemberInfoRepository memberInfoRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final TheaterRepository theaterRepository;
    private final PostService postService;
    // 전체 회원 조회
    public List<MemberDto> getMemberList() {
        List<MemberDto> memberDtoList = new ArrayList<>();
        List<MemberInfo> memberInfoList = memberInfoRepository.findAll();
        for(MemberInfo info : memberInfoList) {
            MemberDto memberDto = new MemberDto();
            memberDto.setUserId(info.getUserId());
            memberDto.setUserName(info.getUserName());
            memberDto.setUserPhone(info.getUserPhone());
            memberDto.setUserEmail(info.getUserEmail());
            memberDtoList.add(memberDto);
        }
        return memberDtoList;
    }

    // 특정 멤버 상세 정보 조회
    public MemberDto getMember(String userId) {
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

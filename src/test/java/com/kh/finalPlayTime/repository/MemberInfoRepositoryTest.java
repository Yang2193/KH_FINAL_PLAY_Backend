package com.kh.finalPlayTime.repository;

import com.kh.finalPlayTime.entity.MemberInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest // 스프링 컨텍스트를 로드하여 테스트 환경을 설정.
@Transactional // 데이터베이싀 논리적인 작업 단위, 모두 성공하는 경우가 아니면 롤백 (테스트에서는 자동롤백)
@TestPropertySource(locations = "classpath:application-test.properties")
@Slf4j // 로깅 데이터를 처리하기 위해 사용
public class MemberInfoRepositoryTest {

    @Autowired
    private MemberInfoRepository memberInfoRepository;

    @Test
    @DisplayName("멤버 생성 테스트")
    public void createMemberTest(){
        for(int i = 1; i <= 5; i++) {
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setMemberId("test" + i);
            memberInfo.setName("테스트" + i);
            memberInfo.setEmail(i+"@gmail.com");
            memberInfo.setJoinDate(LocalDateTime.now());
            memberInfo.setPhoneNum("010-2732-231"+i);
            memberInfo.setPwd("sphb802" + i);
            memberInfo.setNickname("테스트닉네임" + i);
            MemberInfo saveMember = memberInfoRepository.save(memberInfo);
            System.out.println("결과 : " + saveMember);
        }
    }
    @Test
    @DisplayName("멤버 아이디 테스트")
    public void findByIdTest(){
        this.createMemberTest();
        List<MemberInfo> list = memberInfoRepository.findByMemberId("test1");
        for(MemberInfo e : list){
            System.out.println("결과 : " + e);
        }
    }
}

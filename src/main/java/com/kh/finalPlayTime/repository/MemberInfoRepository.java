package com.kh.finalPlayTime.repository;

import com.kh.finalPlayTime.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberInfoRepository extends JpaRepository<MemberInfo, Long> {
    List<MemberInfo> findByUserId(String userId);
    List<MemberInfo> findByUserIdAndUserPw(String userId, String userPw);
    MemberInfo findByUserNameAndUserEmail(String userName, String userEmail);
    MemberInfo findByUserIdAndUserNameAndUserEmail(String userId, String userName, String userEmail);
}

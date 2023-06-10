package com.kh.finalPlayTime.repository;

import com.kh.finalPlayTime.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberInfoRepository extends JpaRepository<MemberInfo, Long> {
    List<MemberInfo> findByMemberId(String id);
}

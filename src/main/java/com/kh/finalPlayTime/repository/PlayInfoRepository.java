package com.kh.finalPlayTime.repository;

import com.kh.finalPlayTime.entity.PlayInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public interface PlayInfoRepository extends JpaRepository <PlayInfo, Long>{
    List<PlayInfo> findByPlayId(String playId);

    List<PlayInfo> findByTitleContaining(String title);

}

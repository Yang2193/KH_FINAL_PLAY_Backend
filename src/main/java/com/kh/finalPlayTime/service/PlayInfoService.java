package com.kh.finalPlayTime.service;

import com.kh.finalPlayTime.entity.PlayInfo;
import com.kh.finalPlayTime.repository.PlayInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.List;

@ToString
@Service
@RequiredArgsConstructor
public class PlayInfoService {
    private final PlayInfoRepository playInfoRepository;

    public List<PlayInfo> findInfo (String playId) {
        return playInfoRepository.findByPlayId(playId);
    }
}

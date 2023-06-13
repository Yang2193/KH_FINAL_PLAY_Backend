package com.kh.finalPlayTime.service;

import com.kh.finalPlayTime.dto.PlayListDto;
import com.kh.finalPlayTime.entity.PlayInfo;
import com.kh.finalPlayTime.repository.PlayInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PlayListService {
    private final PlayInfoRepository pir;
    public List<PlayListDto> getPlayList(){
        List<PlayInfo> plays = pir.findAll();
        List<PlayListDto> playListDtoList = new ArrayList<>();
        for(PlayInfo e : plays){
            PlayListDto playListDto = new PlayListDto();
            playListDto.setPlayId(e.getPlayId());
            playListDto.setTitle(e.getTitle());
            playListDto.setImageUrl(e.getPosterImageUrl());
            playListDto.setPeriodStart(e.getPeriodStart());
            playListDto.setPeriodEnd(e.getPeriodEnd());
            playListDto.setTheaterName(e.getTheaterName());
            playListDtoList.add(playListDto);
        }
        return playListDtoList;
    }
}

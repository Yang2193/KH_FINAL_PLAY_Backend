package com.kh.finalPlayTime.service;

import com.kh.finalPlayTime.dto.PlayInfoDto;
import com.kh.finalPlayTime.dto.PlayListDto;
import com.kh.finalPlayTime.entity.PlayInfo;
import com.kh.finalPlayTime.repository.PlayInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    //제목검색 메소드 -> AND로 수정할 지 말지 고민 중. 배우도 추가하면 OR로 쓰는 레포지토리 메소드로 바꿀 듯?
    public List<PlayListDto> searchPlayList(String[] keywords){
        List<PlayInfo> list = new ArrayList<>();
        for(String e : keywords){
            List<PlayInfo> playInfoList = pir.findByTitleContaining(e);
            list.addAll(playInfoList);
        }
        // 중복 결과 제거
        Set<PlayInfo> distinctList = new HashSet<>(list);
        List<PlayInfo> resultList = new ArrayList<>(distinctList);

        //Dto에 옮기기
        List<PlayListDto> playListDtoList = new ArrayList<>();
        for(PlayInfo e : resultList){
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
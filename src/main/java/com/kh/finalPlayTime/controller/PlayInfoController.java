package com.kh.finalPlayTime.controller;

import com.kh.finalPlayTime.dto.PlayDetailInfoDto;
import com.kh.finalPlayTime.dto.TheaterDto;
import com.kh.finalPlayTime.entity.Seat;
import com.kh.finalPlayTime.entity.Theater;
import com.kh.finalPlayTime.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value="/play")
public class PlayInfoController {
    private final PlayInfoApiService apiService;
    private final PlayDetailInfoApiService detailApiService;
    private final TheaterApiService theaterApiService;

    //연극 상세정보 찾기
    @GetMapping("/{mt20id}")
    public List<PlayDetailInfoDto> getPlayDetail(@PathVariable String mt20id){
        String result = detailApiService.playDetailInfoApi(mt20id);
        return detailApiService.detailFromJsonObj(result);
    }

    // 연극 목록 DB에 저장하는 컨트롤러 부분 , 나중에 자바에서 일정시간마다 자동실행되게 할 것.
    @GetMapping("/saveList")
    public ResponseEntity<Boolean> saveList(){
        boolean isSuccess = false;
        String result = apiService.playListApi();
        isSuccess = apiService.listFromJsonObj(result);
        return new ResponseEntity<>(isSuccess, HttpStatus.OK);
    }

    // 선택한 극장 상세정보 불러오기
    @GetMapping("/theater/{mt10id}")
    public List<TheaterDto> getTheaterDetail(@PathVariable String mt10id) {
        String result = theaterApiService.TheaterDetailApi(mt10id);
        return theaterApiService.detailFromJsonObj(result);
    }
}

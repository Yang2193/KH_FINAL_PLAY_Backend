package com.kh.finalPlayTime.controller;

import com.kh.finalPlayTime.dto.PlayDetailInfoDto;
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
    private final PlayInfoService playInfoService;
    private final SeatService seatService;
    private final TheaterService theaterService;
    private final PlayInfoApiService apiService;
    private final PlayDetailInfoApiService detailApiService;

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

    @GetMapping("/selectSeat")
    public ResponseEntity<List<Seat>> selectSeat (@RequestParam String id){
        List<Seat> list = seatService.findSeat(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/selectTheater")
    public ResponseEntity<List<Theater>> selectTheater (@RequestParam String id){
        List<Theater> list = theaterService.findTheater(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}

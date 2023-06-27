package com.kh.finalPlayTime.controller;

import com.kh.finalPlayTime.dto.ReserveDto;
import com.kh.finalPlayTime.entity.Reserve;
import com.kh.finalPlayTime.service.ReserveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/reserves")
@RequiredArgsConstructor
@Slf4j
public class ReserveController {
    private final ReserveService reserveService;


    // 예매목록 엔티티로 불러오기
    @GetMapping("resList")
    public ResponseEntity <List<Reserve>> getReserve(String userId){
        List<Reserve> list = reserveService.findRes(userId);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    // 예매 목록 조회
    @GetMapping("reserveList")
    public ResponseEntity<List<ReserveDto>> getResList(String id) {
        List<ReserveDto> reserveDtos = reserveService.findReserveInfo(id);
        return new ResponseEntity<>(reserveDtos, HttpStatus.OK);
    }
    // 예매 추가
    @PostMapping("/add")
    public ResponseEntity<Reserve> insertReserve(@RequestBody ReserveDto reserveDto) {
        Reserve savedReserve = reserveService.addReserve(
                reserveDto.getSeatPosition(),
                reserveDto.getSeeDate(),
                reserveDto.getReserveDate(),
                reserveDto.getUserId(),
                reserveDto.getPlayId());
        return ResponseEntity.ok(savedReserve);
    }
}

package com.kh.finalPlayTime.controller;

import com.kh.finalPlayTime.entity.Actor;
import com.kh.finalPlayTime.entity.PlayInfo;
import com.kh.finalPlayTime.entity.Seat;
import com.kh.finalPlayTime.entity.Theater;
import com.kh.finalPlayTime.service.ActorService;
import com.kh.finalPlayTime.service.PlayInfoService;
import com.kh.finalPlayTime.service.SeatService;
import com.kh.finalPlayTime.service.TheaterService;
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
@RequestMapping(value="/api")
public class PlayInfoController {
    private final PlayInfoService playInfoService;
    private final ActorService actorService;
    private final SeatService seatService;
    private final TheaterService theaterService;

    @GetMapping("/selectPlayInfo")
    public ResponseEntity<List<PlayInfo>> selectPlayInfo (@RequestParam String id){
        List<PlayInfo> list = playInfoService.findInfo(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/selectActor")
    public ResponseEntity<List<Actor>> selectActor (@RequestParam String id){
        List<Actor> list = actorService.findActor(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
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

package com.kh.finalPlayTime.controller;


import com.kh.finalPlayTime.dto.PlayInfoDto;
import com.kh.finalPlayTime.service.PlayInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value="/api")
public class PlayInfoController {
    private final PlayInfoService playInfoService;

    @GetMapping("/selectPlayInfo")
    public ResponseEntity<List<PlayInfoDto>> selectPlayInfo (){
        List<PlayInfoDto> list = new ArrayList<>();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}

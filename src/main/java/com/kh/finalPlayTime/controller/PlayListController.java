package com.kh.finalPlayTime.controller;

import com.kh.finalPlayTime.dto.PlayListDto;
import com.kh.finalPlayTime.service.PlayListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequestMapping("/playlist")
@RequiredArgsConstructor
public class PlayListController {
    private final PlayListService pls;

    @GetMapping("/all")
    public ResponseEntity<List<PlayListDto>> getPlayList(){
        List<PlayListDto> list = pls.getPlayList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}

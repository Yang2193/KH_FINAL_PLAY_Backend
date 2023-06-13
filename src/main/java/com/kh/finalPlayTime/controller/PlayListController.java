package com.kh.finalPlayTime.controller;

import com.kh.finalPlayTime.dto.PlayListDto;
import com.kh.finalPlayTime.service.PlayListService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequestMapping("/playList")
@RequiredArgsConstructor
public class PlayListController {
    private final PlayListService pls;

    @GetMapping("/all")
    public ResponseEntity<List<PlayListDto>> getPlayList(){
        System.out.println("전체 연극 목록 불러오기 컨트롤러 작동");
        List<PlayListDto> list = pls.getPlayList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<PlayListDto>> searchList(@RequestBody Keyword keyword){
        System.out.println("연극제목 검색 컨트롤러 작동");
        List<PlayListDto> list = pls.searchPlayList(keyword.keywords);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Getter
    @Setter
    private static class Keyword{
        private String[] keywords;
    }

}

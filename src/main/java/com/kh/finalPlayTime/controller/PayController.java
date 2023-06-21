package com.kh.finalPlayTime.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@Slf4j
public class PayController {
     //예매 컨트롤러
//    @PostMapping("/payment")
//    public ResponseEntity<Object> paymentReserve(@RequestBody paymentReserveDTO){
//
//        //예매 생성
//        reserveService.createReserve(paymentReserveDTO);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }



}

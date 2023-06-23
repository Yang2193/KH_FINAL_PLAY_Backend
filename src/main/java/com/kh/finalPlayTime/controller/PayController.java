package com.kh.finalPlayTime.controller;

import com.kh.finalPlayTime.dto.PayApproveDto;
import com.kh.finalPlayTime.service.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@Controller
@Slf4j
public class PayController {
    // 카카오페이 결제 요청
    @Autowired
    private PayService payService;

    @PostMapping("/payment/ready")
    public String payReady(@RequestParam String price) {
        return "redirect:" + payService.kakaoPayReady(price);
    }

    // 카카오페이 결제 승인 요청
    @GetMapping("/payment/success")
    public ResponseEntity afterPayRequest(@RequestParam("pg_token") String pgToken) {

        PayApproveDto kakaoApprove = payService.approveResponse(pgToken);

        return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
    }

    // 카카오페이 결제 취소시 실행 url
    @GetMapping("/payment/cancel")
    public String payCancel() {
        log.error("결제 취소!!!!!!!!!!!!!");
        return "redirect:/"; //
    }

    // 카카오페이 결제 실패시 실행 url
    @GetMapping("/payment/fail")
    public String payFail() {
        log.error("결제 실패!!!!!!!!!!!!!!");
        return "redirect:/"; //
    }

}

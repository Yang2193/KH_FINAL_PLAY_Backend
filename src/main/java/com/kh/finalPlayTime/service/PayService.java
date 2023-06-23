package com.kh.finalPlayTime.service;

import com.kh.finalPlayTime.dto.PayApproveDto;
import com.kh.finalPlayTime.dto.PayReadyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PayService {
//    private String adminKey = "9eb9b92fd6c7162db3af0773c6c1e2ea"; // 카카오 api를 이용하기 위한 admin 키
//
//    private String url = "https://kapi.kakao.com/v1/payment"; // 카카오페이 api 호출주소
//
//    private PayReadyDto payReadyDTO;
//    private PayApproveDto payConfirmDTO;

//    public String payReady(String price) {
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        // 카카오가 요구한 값을 서버로 요청할 Header
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "KakaoAK " + adminKey); // 카카오 인증키
//        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8"); // 넘겨줄 타입
//
//        // 카카오가 요구한 결제 요청값을 담아줄 Body
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("cid", "TC0ONETIME"); // 가맹점 코드 (필수), 테스트용 고정값
//        params.add("partner_order_id", "mute001"); // 가맹점 주문번호 (필수)
//        params.add("partner_user_id", "mute"); // 가맹점 회원id (필수)
//        params.add("item_name", "Play"); // 상품명 (필수) -> 변수받아오기
//        params.add("item_code", "Play id"); // 상품코드 -> 변수받아오기
//        params.add("quantity", "1"); // 상품수량 (필수) -> 변수받아오기
//        params.add("total_amount", price); // 상품총액 (필수) -> 변수받아오기
//        params.add("tax_free_amount", "0"); // 상품 비과세 금액 (필수)
//        params.add("approval_url", "http://localhost:3000/payEnd"); // 결제 성공시 url -> 결제완료페이지
//        params.add("cancel_url", "http://localhost:3000/payCancel"); // 결제 취소시 url
//        params.add("fail_url", "http://localhost:3000/payFail"); // 결제 실패시 url
//
//        // 하나의 map안에 header와 parameter값을 담아줌.
//        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);
//
//        payReadyDTO = restTemplate.postForObject(url + "/ready", body, PayReadyDto.class);
////        log.info("" + PayReadyDTO);
////        log.info(PayReadyDTO.getNext_redirect_pc_url());
//
//        return payReadyDTO.getNext_redirect_pc_url(); // 요청 후 응답받는 리다이렉트 url을 리턴함
//    }


    static final String admin_Key = "9eb9b92fd6c7162db3af0773c6c1e2ea";
    private PayReadyDto kakaoReady;

    public PayReadyDto kakaoPayReady(String price) {

        // 카카오페이 요청 양식
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", "TC0ONETIME");
        parameters.add("partner_order_id", "play001");
        parameters.add("partner_user_id", "play");
        parameters.add("item_name", "연극");
        parameters.add("quantity", "1");
        parameters.add("total_amount", price);
        parameters.add("vat_amount", "0");
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", "http://localhost:8111/payment/success"); // 성공 시 redirect url
        parameters.add("cancel_url", "http://localhost:8111/payment/cancel"); // 취소 시 redirect url
        parameters.add("fail_url", "http://localhost:8111/payment/fail"); // 실패 시 redirect url

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        kakaoReady = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/ready",
                requestEntity,
                PayReadyDto.class);
        return kakaoReady;
    }
    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = "KakaoAK " + admin_Key;
        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        return httpHeaders;
    }



    // 결제 승인 단계
//    public PayApproveDto payInfo(String pg_token) {
//
////        log.info("PayConfirmDTO................");
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        // 서버로 요청할 Header
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "KakaoAK " + adminKey);
//        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        // 카카오가 요구한 결제 승인 요청값을 담아줄 Body
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("cid", "TC0ONETIME");
//        params.add("tid", payReadyDTO.getTid()); // 결제요청단계에서 받은 tid를 넘겨줌
//        params.add("partner_order_id", "mute001");
//        params.add("partner_user_id", "mute");
//        params.add("pg_token", pg_token); // 결제 승인이 되면 생성되는 토큰을 넘겨줌
//        params.add("total_amount", "59000"); // 상품총액 -> 변수받아오기
//
//        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);
//
//        try {
//            payConfirmDTO = restTemplate.postForObject(url + "/approve", body, PayApproveDto.class);
////            log.info("" + PayConfirmDTO);
//            return payConfirmDTO;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } return null;
//    }

    public PayApproveDto approveResponse(String pgToken) {

        // 카카오 요청
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", "TC0ONETIME");
        parameters.add("tid", kakaoReady.getTid());
        parameters.add("partner_order_id", "play001");
        parameters.add("partner_user_id", "play");
        parameters.add("pg_token", pgToken);

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        PayApproveDto approveResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/approve",
                requestEntity,
                PayApproveDto.class);

        return approveResponse;
    }
}

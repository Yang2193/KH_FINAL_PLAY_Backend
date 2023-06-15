package com.kh.finalPlayTime.service;

import com.kh.finalPlayTime.entity.PlayInfo;
import com.kh.finalPlayTime.repository.PlayInfoRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class PlayInfoApiService {
    @Value("${api.serviceKey}")
    private String key;
    private final PlayInfoRepository playInfoRepository;

    public String playListApi(){
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        UriComponents uri = UriComponentsBuilder
                .fromUriString("https://www.kopis.or.kr")
                .path("/openApi/restful/pblprfr/")
                .queryParam("service", key) // 인증키 (필수)
                .queryParam("stdate", 20230615) // 공연시작일 (필수)
                .queryParam("eddate", 20230901) // 공연종료일 (필수)
                .queryParam("cpage", 1) // 현재 페이지 (필수)
                .queryParam("rows", 100) // 페이지 당 목록 수 (필수)
                .queryParam("signgucode", 11) // 지역코드(11 = 서울)
                .queryParam("shcate", "AAAA") // 장르코드(AAAA = 연극)
                .encode() // utf-8 로 인코딩
                .build();

        ResponseEntity<String> responseEntity = rest.exchange(uri.toUri(), HttpMethod.GET, requestEntity, String.class);
        String response = responseEntity.getBody();
        return response;
    }

    public boolean listFromJsonObj(String result){
        // xml 데이터를 json 데이터로 변환
        JSONObject xmlToJson = XML.toJSONObject(result);

        // JSONObject로 데이터 가져오기
        JSONObject jsonObj = xmlToJson.getJSONObject("dbs");

        // 배열형식이니 JSONArray로 가져오기
        JSONArray jsonArr = jsonObj.getJSONArray("db");

        //db에 저장
        for(int i = 0; i < jsonArr.length(); i++){
            JSONObject item = (JSONObject) jsonArr.get(i);
            PlayInfo playInfo = new PlayInfo(item);
            playInfoRepository.save(playInfo);
        }
        System.out.println("DB에 저장");
        return true;
    }
}

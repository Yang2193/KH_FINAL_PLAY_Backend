package com.kh.finalPlayTime.service;


import com.kh.finalPlayTime.entity.Reserve;

import java.util.Objects;
import java.util.UUID;

public class ReserveService {

//    @Transactional
//    @Override
//    public void createReserve(PaymentReserveDTO paymentReserveDTO) {
//        //예매 회원 조회
//        Member reserveMember = memberRepository.findByIndex(paymentReserveDTO.getMemberIndex())
//                .orElseThrow(() -> new CustomException(CustomErrorCode.EMPTY_MEMBER));
//        //상세 예매 정보 조회
//        ReserveTimeSeatPrice reserveDetail = reserveTimeSeatPriceRepository.findById(paymentReserveDTO.getReserveTimeSeatPriceId())
//                .orElseThrow(() -> new CustomException(CustomErrorCode.EMPTY_RESERVE_TIME_SEAT_PRICE));
//
//        //예매 정보
//        ReserveTime reserveTime = reserveDetail.getReserveTime();
//
//        //좌석 정보
//        String seatInfo = reserveDetail.getSeatPrice().getSeat();
//
//        //포인트 유효성 확인
//        if (reserveMember.getPoint() < paymentReserveDTO.getPoint()) {
//            throw new CustomException(CustomErrorCode.ERROR_POINT_INFO);
//        }
//
//        //잔여 예매 좌석 유효성 확인 (상시 상품은 제외)
//        //한정 상품이고 (총 좌석이 0이 아니고) 남은 좌석이 주문 수량보다 작으면
//        if (reserveDetail.getTotalQuantity() != 0 && reserveDetail.getRemainQuantity() < paymentReserveDTO.getQuantity()) {
//            throw new CustomException(CustomErrorCode.ERROR_REMAIN_QUANTITY);
//        }
//
//        //상세 예매 정보의 잔여 수량 갱신
//        reserveDetail.minusQuantity(paymentReserveDTO.getQuantity());
//
//        //관리자 차트 갱신 위한 값
//        Long cumuAmount = 0L;
//        Long cumuDiscount = 0L;
//
//        String randomIdKey;
//        do {
////                String randomIdKey = UUID.randomUUID().toString().substring(0, 5);
//            randomIdKey = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase().substring(0, 32);
//
//            //동일한 아이디가 없어야 무한루프 탈출
//        } while (reserveRepository.findByTicket(randomIdKey).isPresent());
//        //예매 아이디 생성 및 중복 방지
//        for (int count = 1; count <= paymentReserveDTO.getQuantity(); count++) {
//
//            cumuAmount += paymentReserveDTO.getAmount();
//            cumuDiscount += paymentReserveDTO.getPoint();
//
//            //예매 엔티티 생성 및 저장
//            Reserve reserve = new Reserve().toEntity(randomIdKey, count, reserveTime, seatInfo, reserveDetail.getIndex(), paymentReserveDTO, reserveMember);
//            reserveRepository.save(reserve);
//
//            //카카오페이일 경우
//            if (Objects.equals(paymentReserveDTO.getMethod(), "KAKAOPAY")) {
//                //카카오페이인데 TID가 없다면
//                if (Objects.isNull(paymentReserveDTO.getKakaoTID())) {
//                    throw new CustomException(CustomErrorCode.EMPTY_KAKAO_TID);
//                }
//                if (Objects.isNull(paymentReserveDTO.getKakaoTaxFreeAmount())) {
//                    throw new CustomException(CustomErrorCode.EMPTY_KAKAO_TAX_FREE);
//                }
//                //카카오페이 엔티티 생성 및 저장
//                KakaoPay kakaoPay = new KakaoPay().toEntity(paymentReserveDTO.getKakaoTID(), reserveMember, reserve, paymentReserveDTO.getKakaoTaxFreeAmount());
//                kakaoPayRepository.save(kakaoPay);
//            }
//        }

}

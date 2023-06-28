package com.kh.finalPlayTime.service;

import com.kh.finalPlayTime.dto.ReserveDto;
import com.kh.finalPlayTime.entity.MemberInfo;
import com.kh.finalPlayTime.entity.PlayInfo;
import com.kh.finalPlayTime.entity.Reserve;
import com.kh.finalPlayTime.repository.MemberInfoRepository;
import com.kh.finalPlayTime.repository.PlayInfoRepository;
import com.kh.finalPlayTime.repository.ReserveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Transactional
@Service
@RequiredArgsConstructor
public class ReserveService {
    private final ReserveRepository reserveRepository;
    private final PlayInfoRepository playInfoRepository;
    private final MemberInfoRepository memberInfoRepository;

    // 엔티티로 조회
    public List<Reserve> findReserveList (String userId) {
        return reserveRepository.findByMemberInfoUserId(userId);
    }
    // Dto로 조회
    public List<ReserveDto> findReserveInfo(String userId) {
        List<Reserve> reserveList = reserveRepository.findByMemberInfoUserId(userId);
        List<ReserveDto> reserveDtos = new ArrayList<>();

        for (Reserve reserve : reserveList) {
            ReserveDto reserveDto = new ReserveDto();
            reserveDto.setReserveId(reserve.getId());
            reserveDto.setReserveDate(reserve.getReserveDate());
            reserveDto.setSeatPosition(reserve.getSeatPosition());
            reserveDto.setPlayId(reserve.getPlayInfo().getPlayId());
            reserveDto.setUserId(reserve.getMemberInfo().getUserId());
            reserveDto.setSeeDate(reserve.getSeeDate());
            reserveDtos.add(reserveDto);
        }
        return reserveDtos;
    }
    // 예매 등록
    public ReserveDto addReserve(String userId,String playId,String reserveDate, String seeDate,String seatPosition ){
        Reserve reserve = new Reserve();
        // 회원 정보 설정
        Optional<MemberInfo> memberInfoOptional = memberInfoRepository.findByUserId(userId);
        if (memberInfoOptional.isEmpty()) {
            throw new IllegalArgumentException("Member not found");
        }
        MemberInfo memberInfo = memberInfoOptional.get();
        reserve.setMemberInfo(memberInfo);

        // 플레이 정보 설정
        Optional<PlayInfo> playInfoOptional = playInfoRepository.findByPlayId(playId);
        if (playInfoOptional.isEmpty()) {
            throw new IllegalArgumentException("Play not found");
        }
        PlayInfo playInfo = playInfoOptional.get();
        reserve.setPlayInfo(playInfo);
        reserve.setReserveDate(reserveDate);
        reserve.setSeeDate(seeDate);
        reserve.setSeatPosition(seatPosition);
        reserveRepository.save(reserve);

        ReserveDto reserveDto =new ReserveDto();
        reserveDto.setReserveDate(reserve.getReserveDate());
        reserveDto.setReserveId(reserve.getId());
        reserveDto.setUserId(reserve.getMemberInfo().getUserId());
        reserveDto.setPlayId(reserve.getPlayInfo().getPlayId());
        reserveDto.setSeeDate(reserve.getSeeDate());
        reserveDto.setSeatPosition(reserve.getSeatPosition());
        return reserveDto;
    }
}


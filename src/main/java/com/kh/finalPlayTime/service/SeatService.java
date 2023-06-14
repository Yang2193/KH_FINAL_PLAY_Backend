package com.kh.finalPlayTime.service;

import com.kh.finalPlayTime.entity.Seat;
import com.kh.finalPlayTime.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;

    public List<Seat> findSeat (String id) {
        return seatRepository.findByPlayInfoPlayId(id);
    }
}

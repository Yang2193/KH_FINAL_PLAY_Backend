package com.kh.finalPlayTime.service;

import com.kh.finalPlayTime.entity.Actor;
import com.kh.finalPlayTime.entity.Theater;
import com.kh.finalPlayTime.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class TheaterService {
    private final TheaterRepository theaterRepository;

    public List<Theater> findTheater (String id) {
        return theaterRepository.findByPlayId(id);
    }
}

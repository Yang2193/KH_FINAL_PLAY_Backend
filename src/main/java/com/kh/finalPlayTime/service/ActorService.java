package com.kh.finalPlayTime.service;

import com.kh.finalPlayTime.entity.Actor;
import com.kh.finalPlayTime.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class ActorService {
    private final ActorRepository actorRepository;

    public List<Actor> findActor (String id) {
        List<Actor> actors = actorRepository.findByPlayInfoPlayId(id);
        return actors;
    }

}

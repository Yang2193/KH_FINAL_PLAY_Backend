package com.kh.finalPlayTime.repository;

import com.kh.finalPlayTime.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface ActorRepository extends JpaRepository<Actor, Long> {
    List<Actor> findByPlayInfoPlayId(String playId);
}

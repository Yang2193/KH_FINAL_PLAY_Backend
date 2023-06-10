package com.kh.finalPlayTime.repository;

import com.kh.finalPlayTime.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {

}

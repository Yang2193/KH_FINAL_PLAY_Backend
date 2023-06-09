package com.kh.finalPlayTime.repository;

import com.kh.finalPlayTime.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheaterRepository extends JpaRepository<Theater,String> {
    List<Theater> findByPlayId(String playId);

}

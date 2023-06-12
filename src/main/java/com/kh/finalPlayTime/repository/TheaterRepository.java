package com.kh.finalPlayTime.repository;

import com.kh.finalPlayTime.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface TheaterRepository extends JpaRepository<Theater, Long> {
    List<Theater> findByPlayId(String playId);

}

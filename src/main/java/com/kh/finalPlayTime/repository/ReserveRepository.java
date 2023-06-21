package com.kh.finalPlayTime.repository;

import com.kh.finalPlayTime.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve,Long> {
}

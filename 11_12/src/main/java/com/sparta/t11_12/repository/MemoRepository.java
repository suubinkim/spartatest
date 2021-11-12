package com.sparta.t11_12.repository;

import com.sparta.t11_12.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo,String> {
}

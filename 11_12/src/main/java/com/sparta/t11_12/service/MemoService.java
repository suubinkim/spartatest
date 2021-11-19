package com.sparta.t11_12.service;

import com.sparta.t11_12.domain.Memo;
import com.sparta.t11_12.dto.MemoRequestDto;
import com.sparta.t11_12.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemoService {
    private final MemoRepository memoRepository;

    public List<Memo> getMemos(){
        return memoRepository.findAll();
    }

    @Transactional
    public Memo createMemo(MemoRequestDto requestDto){
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        return memo;

    }
}

package com.example.vuebackboard.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @DisplayName("1. 게시글 보기")
    @Test
    void test_1(){
        List<BoardEntity> list = boardRepository.findAll();

        Optional<BoardEntity> board = boardRepository.findById(1L);

        System.out.println(list);
        System.out.println(board.get());


    }

}
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
    }

    @DisplayName("2. 게시글 100개 생성")
    @Test
    void test_2(){
        String sql;

        for (int i = 1; i <= 100; i++) {
            sql = "INSERT INTO BOARD (IDX, TITLE, CONTENTS, AUTHOR, CREATED_AT) VALUES (" + i + ", '게시글 제목" + i + "', '게시글 내용" + i + "', '작성자" + i + "', '2022-02-18 23:24:00');";
            System.out.println(sql);
        }
    }

}
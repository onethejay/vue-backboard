package com.example.vuebackboard.web;

import com.example.vuebackboard.entity.BoardEntity;
import com.example.vuebackboard.entity.BoardRepositoryCustom;
import com.example.vuebackboard.model.SearchCondition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BoardControllerTest {

    @Autowired
    BoardRepositoryCustom boardRepositoryCustom;

    @Test
    void boardCustomTest() {
        SearchCondition searchCondition = SearchCondition.builder()
                .sk("author")
                .sv("작성자1")
                .build();

        List<BoardEntity> list = boardRepositoryCustom.findAllBySearchCondition(PageRequest.of(0, 20), searchCondition);

        list.forEach(System.out::println);
    }

}
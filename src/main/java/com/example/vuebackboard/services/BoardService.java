package com.example.vuebackboard.services;

import com.example.vuebackboard.entity.BoardEntity;
import com.example.vuebackboard.entity.BoardRepository;
import com.example.vuebackboard.entity.BoardRepositoryCustom;
import com.example.vuebackboard.model.Header;
import com.example.vuebackboard.model.Pagination;
import com.example.vuebackboard.model.SearchCondition;
import com.example.vuebackboard.web.dtos.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardRepositoryCustom boardRepositoryCustom;

    /**
     * 게시글 목록 가져오기
     */
    public Header<List<BoardDto>> getBoardList(Pageable pageable, SearchCondition searchCondition) {
        List<BoardDto> dtos = new ArrayList<>();

        Page<BoardEntity> boardEntities = boardRepositoryCustom.findAllBySearchCondition(pageable, searchCondition);
        boardEntities.forEach(entity -> dtos.add(BoardDto.builder()
                .idx(entity.getIdx())
                .title(entity.getTitle())
                .contents(entity.getContents())
                .author(entity.getAuthor())
                .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .build()));

        Pagination pagination = new Pagination(
                (int) boardEntities.getTotalElements()
                , pageable.getPageNumber() + 1
                , pageable.getPageSize()
                , 10
        );

        return Header.OK(dtos, pagination);
    }

    /**
     * 게시글 가져오기
     */
    public BoardDto getBoard(Long id) {
        BoardEntity entity = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return BoardDto.builder()
                .idx(entity.getIdx())
                .title(entity.getTitle())
                .contents(entity.getContents())
                .author(entity.getAuthor())
                .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .build();
    }

    /**
     * 게시글 등록
     */
    public BoardEntity create(BoardDto boardDto) {
        BoardEntity entity = BoardEntity.builder()
                .title(boardDto.getTitle())
                .contents(boardDto.getContents())
                .author(boardDto.getAuthor())
                .createdAt(LocalDateTime.now())
                .build();
        return boardRepository.save(entity);
    }

    /**
     * 게시글 수정
     */
    public BoardEntity update(BoardDto boardDto) {
        BoardEntity entity = boardRepository.findById(boardDto.getIdx()).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        entity.setTitle(boardDto.getTitle());
        entity.setContents(boardDto.getContents());
        return boardRepository.save(entity);
    }

    /**
     * 게시글 삭제
     */
    public void delete(Long id) {
        BoardEntity entity = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        boardRepository.delete(entity);
    }
}
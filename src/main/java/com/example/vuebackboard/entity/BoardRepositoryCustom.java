package com.example.vuebackboard.entity;

import com.example.vuebackboard.model.SearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.vuebackboard.entity.QBoardEntity.boardEntity;

@RequiredArgsConstructor
@Repository
public class BoardRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public Page<BoardEntity> findAllBySearchCondition(Pageable pageable, SearchCondition searchCondition) {
        List<BoardEntity> results = queryFactory.selectFrom(boardEntity)
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(boardEntity.idx.desc())
                .fetch();

        return new PageImpl<>(results, pageable, results.size());
    }

    private BooleanExpression searchKeywords(String sk, String sv) {
        if("author".equals(sk)) {
            if(StringUtils.hasLength(sv)) {
                return boardEntity.author.contains(sv);
            }
        } else if ("title".equals(sk)) {
            if(StringUtils.hasLength(sv)) {
                return boardEntity.title.contains(sv);
            }
        } else if ("contents".equals(sk)) {
            if(StringUtils.hasLength(sv)) {
                return boardEntity.contents.contains(sv);
            }
        }

        return null;
    }
}

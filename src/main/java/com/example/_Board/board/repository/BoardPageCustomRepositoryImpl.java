package com.example._Board.board.repository;

import com.example._Board.board.controller.response.BoardPageResponse;
import com.example._Board.board.controller.response.BoardResponse;
import com.example._Board.board.controller.response.QBoardPageResponse;

import com.example._Board.board.domain.Board;

import com.example._Board.board.domain.QBoard;
import com.example._Board.board.domain.QCategory;
import com.example._Board.board.domain.BoardSortType;

import com.example._Board.user.domain.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BoardPageCustomRepositoryImpl implements BoardPageCustomRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    // 정렬 조건에 따른 페이징 처리
    @Override
    @Transactional(readOnly = true)
    public Page<BoardResponse> findBoardsBySort(BoardSortType sortType, Pageable pageable) {
        QBoard board = QBoard.board;

        JPAQuery<Board> query = queryFactory
                .selectFrom(board)
                .where(board.isNotice.isFalse());

        switch (sortType) {
            case VIEW_DESC -> query.orderBy(board.viewCount.asc());
            case VIEW_ASC -> query.orderBy(board.viewCount.desc());
            case LIKE_DESC -> query.orderBy(board.likeCount.asc());
            case LIKE_ASC -> query.orderBy(board.likeCount.desc());
        }

        List<Board> content = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(board.count())
                .from(board)
                .where(board.isNotice.isFalse())
                .fetchOne();


        List<BoardResponse> responseContent = content.stream()
                .map(BoardResponse::new)
                .collect(Collectors.toList());

        return new PageImpl<>(responseContent, pageable, total);
    }

    // 공지글 + 일반글 순서 페이징 처리 (중복 X)
    @Override
    @Transactional(readOnly = true)
    public Page<BoardPageResponse> findBoardsWithNoticeFirst(Pageable pageable) {
        QBoard board = QBoard.board;
        QCategory category = QCategory.category;
        QUser user = QUser.user;

        List<BoardPageResponse> noticeContent = queryFactory
                .select(new QBoardPageResponse(
                        user.id,
                        board.title,
                        board.content,
                        category.categoryName,
                        board.isNotice,
                        board.viewCount,
                        board.likeCount,
                        board.createdAt
                ))
                .from(board)
                .leftJoin(board.user, user)
                .leftJoin(board.category, category)
                .where(board.isNotice.isTrue())
                .orderBy(board.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<BoardPageResponse> commonContent = queryFactory
                .select(new QBoardPageResponse(
                        user.id,
                        board.title,
                        board.content,
                        category.categoryName,
                        board.isNotice,
                        board.viewCount,
                        board.likeCount,
                        board.createdAt
                ))
                .from(board)
                .leftJoin(board.user, user)
                .leftJoin(board.category, category)
                .where(board.isNotice.isFalse())
                .orderBy(board.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        noticeContent.addAll(commonContent);

        long total = queryFactory
                .select(board.count())
                .from(board)
                .fetchOne();

        return new PageImpl<>(noticeContent, pageable, total);
    }
}



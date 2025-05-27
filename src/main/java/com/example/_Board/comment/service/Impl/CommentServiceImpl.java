package com.example._Board.comment.service.Impl;

import com.example._Board.board.domain.Board;
import com.example._Board.board.repository.BoardRepository;
import com.example._Board.comment.controller.request.CommentCreateRequest;
import com.example._Board.comment.controller.request.CommentEditRequest;
import com.example._Board.comment.controller.response.CommentResponse;
import com.example._Board.comment.domain.Comment;
import com.example._Board.comment.repository.CommentRepository;
import com.example._Board.comment.service.CommentService;
import com.example._Board.error.BusinessException;
import com.example._Board.user.domain.User;
import com.example._Board.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example._Board.error.ErrorCode.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    // 댓글 생성
    @Override
    public void commentCreate(Long boardId, CommentCreateRequest commentCreateRequest) {
        log.info("댓글 생성 시작 ");

        User user = getUser();

        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new BusinessException(NOT_FOUND_BOARD));

        Comment comment = Comment.createComment(user, board, commentCreateRequest);

        commentRepository.save(comment);
        commentRepository.flush();


        long commentCount = commentRepository.commentCount(boardId);
        boardRepository.updateCommentCount(boardId, commentCount);

        log.info("현재 댓글 개수: {}", commentCount);

        log.info("댓글 생성 완료 ");
    }

    // 특정게시글의 댓글 전체 조회 + 페이징
    @Override
    @Transactional(readOnly = true)
    public Page<CommentResponse> commentFindAll(Long boardId, Pageable pageable) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new BusinessException(NOT_FOUND_BOARD));

        Page<Comment> commentPage = commentRepository.findAllByBoardWithUser(board, pageable);

//        Page<Comment> commentPage = commentRepository.findAllByBoard(board, pageable);
        return commentPage.map(CommentResponse::new);
    }

    // 댓글 수정
    @Override
    public void commentEdit(Long commentId, CommentEditRequest commentEditRequest) {
        User user = getUser();
        Comment comment = ExistCommentCheck(commentId);
        WriterCommentUserEqLoginUser(user, comment);
        comment.editComment(commentEditRequest);
    }


    // 댓글 삭제
    @Override
    public void commentDelete(Long commentId) {
        User user = getUser();
        Comment comment = ExistCommentCheck(commentId);
        WriterCommentUserEqLoginUser(user, comment);
        commentRepository.delete(comment);
    }

    // 회원 객체 반환
    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = authentication.getName();

        log.info("로그인 회원 {}", loginId);

        return userRepository.findByLoginId(loginId).orElseThrow(
                () -> new BusinessException(NOT_FOUND_USER));
    }

    // 댓글 작성한 회원 일치여부
    private void WriterCommentUserEqLoginUser(User user, Comment comment) {
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new BusinessException(NOT_MATCH_COMMENT);
        }
    }

    // 댓글이 존재하는지?
    private Comment ExistCommentCheck(Long commentId) {

        return commentRepository.findById(commentId).orElseThrow(
                () -> new BusinessException(NOT_FOUND_COMMENT));
    }
}

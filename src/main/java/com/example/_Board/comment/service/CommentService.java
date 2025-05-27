package com.example._Board.comment.service;

import com.example._Board.comment.controller.request.CommentCreateRequest;
import com.example._Board.comment.controller.request.CommentEditRequest;
import com.example._Board.comment.controller.response.CommentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    // 댓글생성
    void commentCreate(Long boardId, CommentCreateRequest commentCreateRequest);

    // 댓글 전체 조회 + 페이징
    Page<CommentResponse> commentFindAll(Long boardId, Pageable pageable);

    // 댓글 수정
    void commentEdit(Long commentId, CommentEditRequest commentEditRequest);

    // 댓글 삭제
    void commentDelete(Long commentId);

}

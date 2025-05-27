package com.example._Board.comment.controller;

import com.example._Board.comment.controller.request.CommentCreateRequest;
import com.example._Board.comment.controller.request.CommentEditRequest;
import com.example._Board.comment.controller.response.CommentResponse;
import com.example._Board.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/board/{boardId}/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public void commentCreate(@PathVariable("boardId") Long boardId,
                              @RequestBody @Valid CommentCreateRequest request) {
        commentService.commentCreate(boardId, request);
    }

    // 댓글 전체 조회
    @GetMapping("/board/{boardId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public Page<CommentResponse> commentFindAll(@PathVariable("boardId") Long boardId,
                                                @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return commentService.commentFindAll(boardId, pageable);
    }

    // 댓글 수정
    @PutMapping("/comment/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void commentEdit(@PathVariable("commentId") Long commentId,
                            @RequestBody @Valid CommentEditRequest request) {
        commentService.commentEdit(commentId, request);
    }

    // 댓글 삭제
    @DeleteMapping("/comment/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void commentDelete(@PathVariable("commentId") Long commentId) {
        commentService.commentDelete(commentId);
    }
}

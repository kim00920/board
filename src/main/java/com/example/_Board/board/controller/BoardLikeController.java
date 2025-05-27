package com.example._Board.board.controller;

import com.example._Board.board.controller.response.BoardLikeResponse;
import com.example._Board.board.service.BoardLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class BoardLikeController {

    private final BoardLikeService boardLikeService;

    // 좋아요 누르기
    @PostMapping("/board/{boardId}/like")
    @ResponseStatus(HttpStatus.OK)
    public void increaseLike(@PathVariable("boardId") Long boardId) {
        boardLikeService.increaseLike(boardId);
    }

    // 좋아요 취소
    @DeleteMapping("/board/{boardId}/like")
    @ResponseStatus(HttpStatus.OK)
    public void decreaseLike(@PathVariable("boardId") Long boardId) {
        boardLikeService.decreaseLike(boardId);
    }

    // 좋아요 전체 조회
    @GetMapping("/board/{boardId}/like")
    @ResponseStatus(HttpStatus.OK)
    public List<BoardLikeResponse> findAllBoardLike(@PathVariable("boardId") Long boardId) {
        return boardLikeService.findAllByBoardId(boardId);
    }
}

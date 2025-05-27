package com.example._Board.board.controller;

import com.example._Board.board.controller.request.BoardCreateRequest;
import com.example._Board.board.controller.request.BoardEditRequest;
import com.example._Board.board.controller.response.BoardPageResponse;
import com.example._Board.board.controller.response.BoardResponse;
import com.example._Board.board.service.BoardService;
import com.example._Board.board.domain.BoardSortType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    // 게시글 생성
    @PostMapping("/board")
    @ResponseStatus(HttpStatus.CREATED)
    public void boardCreate(@RequestPart @Valid BoardCreateRequest request,
                            @RequestPart List<MultipartFile> multipartFile) {
        boardService.boardCreate(request, multipartFile);
    }

    // 게시글 전체 조회 + 페이징 처리
    @GetMapping("/board/all")
    @ResponseStatus(HttpStatus.OK)
    public Page<BoardPageResponse> boardsFindAll(@PageableDefault(size = 5) Pageable pageable) {
        return boardService.boardsFindAll(pageable);
    }


    // 특정 게시글 조회
    @GetMapping("/board/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public BoardResponse boardFindById(@PathVariable("boardId") Long boardId) {
        return boardService.boardFindById(boardId);
    }

    // 게시글 수정
    @PutMapping("/board/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public void boardEdit(@PathVariable("boardId") Long boardId,
                          @RequestPart @Valid BoardEditRequest request,
                          @RequestPart List<MultipartFile> multipartFile) {
        boardService.boardEdit(boardId, request, multipartFile);
    }

    // 게시글 삭제
    @DeleteMapping("/board/{boardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void boardDelete(@PathVariable("boardId") Long boardId) {
        boardService.boardDelete(boardId);
    }


    // 게시글 정렬 조회
    @GetMapping("/board/sorted/{sortType}")
    @ResponseStatus(HttpStatus.OK)
    public Page<BoardResponse> boardsFindBySort(
            @PathVariable("sortType") BoardSortType sortType,
            @PageableDefault Pageable pageable) {
        return boardService.boardsFindBySort(sortType, pageable);
    }

    // 게시글 검색 기능
    @GetMapping("/board/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<BoardResponse> boardFindKeyword(@RequestParam("keyword") String keyword,
                                                Pageable pageable) {
        return boardService.boardFindKeyword(keyword, pageable);
    }

    // 게시글 공지사항 설정
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/board/{boardId}/notice")
    @ResponseStatus(HttpStatus.OK)
    public void boardSetNotice(@PathVariable("boardId") Long boardId) {
        boardService.boardSetNotice(boardId);
    }
}

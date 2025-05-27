package com.example._Board.board.controller.response;

import com.example._Board.board.domain.Board;
import com.example._Board.board.domain.BoardLike;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardLikeResponse {

    private Long boardId;
    private Long userId;
    private String userName;

    public BoardLikeResponse(BoardLike boardLike) {
        this.boardId = boardLike.getBoard().getId();
        this.userId = boardLike.getUser().getId();
        this.userName = boardLike.getUser().getName();
    }
}

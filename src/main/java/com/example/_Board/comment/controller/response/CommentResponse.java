package com.example._Board.comment.controller.response;

import com.example._Board.board.controller.response.ImageResponse;
import com.example._Board.board.domain.Board;
import com.example._Board.board.domain.Image;
import com.example._Board.comment.domain.Comment;
import com.example._Board.comment.domain.ReplyComment;
import com.example._Board.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {

    private Long userId;
    private String userName;
    private Long boardId;
    private String comment;
    private List<ReplyCommentResponse> replyResponse;

    public CommentResponse(Comment comment) {
        this.userId = comment.getUser().getId();
        this.userName = comment.getUser().getName();
        this.boardId = comment.getBoard().getId();
        this.comment = comment.getComment();
        this.replyResponse = comment.getReplyComments() // 프록시 객체임
                .stream() // 이떄 n + 1 발생 부분
                .map(ReplyCommentResponse::new).collect(Collectors.toList());
    }

    public static List<CommentResponse> toCommentResponseList(List<Comment> comments) {
        return comments.stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }



}


package com.example._Board.comment.controller.response;

import com.example._Board.comment.domain.ReplyComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyCommentResponse {

    private String replyComment;

    public ReplyCommentResponse(ReplyComment replyComment) {
        this.replyComment = replyComment.getReplyComment();
    }
}

package com.example._Board.comment.domain;


import com.example._Board.comment.controller.request.ReplyCommentCreateRequest;
import com.example._Board.config.BaseTimeEntity;
import com.example._Board.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "replyComment")
@Entity
public class ReplyComment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "replyComment_id")
    private Long id;

    @Column(nullable = false)
    private Long userId;   //회원번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;   //  댓글

    @Column(nullable = false)
    private String replyComment;  // 대댓글 내용

    @Builder
    public ReplyComment(Long id, Long userId, Comment comment, String replyComment) {
        this.id = id;
        this.userId = userId;
        this.comment = comment;
        this.replyComment = replyComment;
    }

    // 대댓글 수정
    public void editReplyComment(String replyComment) {
        this.replyComment = replyComment;
    }

    // 대댓글 생성
    public static ReplyComment createReplyComment(User user, Comment comment, ReplyCommentCreateRequest replyCommentCreateRequest) {
        return ReplyComment.builder()
                .userId(user.getId())
                .comment(comment)
                .replyComment(replyCommentCreateRequest.getReplyComment())
                .build();
    }
}

package com.example._Board.board.controller.response;

import com.example._Board.board.domain.Board;
import com.example._Board.comment.controller.response.CommentResponse;
import com.example._Board.comment.domain.ReplyComment;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardResponse {

    private Long userId;
    private String title;
    private String content;
    private String categoryName;
    private boolean isNotice;
    private int viewCount;
    private int likeCount;
    private LocalDateTime createdAt;
    private List<ImageResponse> imageList;
    private List<CommentResponse> commentList;


    // 게시글 전체 조회 (댓글 제외)
    public BoardResponse(Board board) {
        this.userId = board.getUser().getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.isNotice = board.isNotice();
        this.viewCount = board.getViewCount();
        this.likeCount = board.getLikeCount();
        this.createdAt = board.getCreatedAt();
        this.categoryName = board.getCategory().getCategoryName();
        this.imageList = ImageResponse.toResponse(board);
    }


    // 매개변수를 받는 생성자 추가
    public BoardResponse( Boolean isNotice, int likeCount) {
        this.isNotice = isNotice;
        this.likeCount = likeCount;
    }

    // 특정 게시글의 모든 정보 (S3 이미지 + 카테고리 + 댓글 + 대댓글)
    public static BoardResponse toBoardResponse(Board board) {
        return BoardResponse.builder()
                .userId(board.getUser().getId())
                .title(board.getTitle())
                .content(board.getContent())
                .categoryName(board.getCategory().getCategoryName())
                .isNotice(board.isNotice())
                .viewCount(board.getViewCount())
                .likeCount(board.getLikeCount())
                .createdAt(board.getCreatedAt())
                .imageList(ImageResponse.toResponse(board))
                .commentList(CommentResponse.toCommentResponseList(board.getComments()))
                .build();
    }


}

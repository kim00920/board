package com.example._Board.board.controller.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardPageResponse {

    private Long userId;
    private String title;
    private String content;
    private String categoryName;
    private boolean isNotice;
    private int viewCount;
    private int likeCount;
    private LocalDateTime createdAt;


    @QueryProjection
    public BoardPageResponse(Long userId, String title, String content, String categoryName, boolean isNotice,
                             int viewCount, int likeCount, LocalDateTime createdAt) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.categoryName = categoryName;
        this.isNotice = isNotice;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
    }
}

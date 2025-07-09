package com.example._Board.board.controller.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LikeNotificationMessage {
    private Long senderId;
    private Long boardId;
    private Long receiverId;
}
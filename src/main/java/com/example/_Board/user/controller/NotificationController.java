package com.example._Board.user.controller;

import com.example._Board.config.security.CustomUserDetails;
import com.example._Board.user.controller.response.NotificationResponse;
import com.example._Board.user.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final NotificationService notificationService;

    // 현재 회원이 읽지 않는 알람 개수
    @GetMapping("/unread")
    @ResponseStatus(HttpStatus.OK)
    public int getUnreadNotificationCount(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return notificationService.notificationUnReadCnt(userDetails.getId());
    }

    // 전체 알림 목록 + 페이징
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<NotificationResponse> getAllNotifications(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PageableDefault(size = 3) Pageable pageable) {
        return notificationService.notificationFindAll(userDetails.getId(), pageable);
    }

    // 회원이 알림 누르면 읽음 처리되게함
    @PutMapping("/{notificationId}/read")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void markAsRead(@PathVariable("notificationId") Long notificationId) {
        notificationService.notificationRead(notificationId);
    }

    // 알림 삭제
    @DeleteMapping("/{notificationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void notificationDelete(@PathVariable("notificationId") Long notificationId) {
        notificationService.notificationDelete(notificationId);
    }


}

package com.example.boardtest.user.service;

import com.example._Board.board.domain.Board;
import com.example._Board.board.repository.BoardRepository;
import com.example._Board.error.BusinessException;
import com.example._Board.user.controller.response.NotificationResponse;
import com.example._Board.user.domain.Notification;
import com.example._Board.user.domain.User;
import com.example._Board.user.repository.NotificationRepository;
import com.example._Board.user.repository.UserRepository;
import com.example._Board.user.service.Impl.NotificationImpl;
import com.example.boardtest.factory.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("알림 단위테스트")
public class NotificationServiceTest {

    @InjectMocks
    private NotificationImpl notificationService;

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BoardRepository boardRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    private User sender;
    private User receiver;
    private Board board;
    private Notification notification;

    @BeforeEach
    void setUp() {
        UserFactory userFactory = new UserFactory(passwordEncoder);
        sender = User.builder().id(1L).loginId("sender").build();
        receiver = userFactory.createUser1();  // receiver = id 1, loginId = "loginId"
        board = Board.builder().id(100L).build();

        notification = Notification.builder()
                .id(10L)
                .sender(sender)
                .receiver(receiver)
                .board(board)
                .message("test message")
                .alreadyRead(false)
                .createdAt(LocalDateTime.now())
                .build();
    }

    private void mockLoginUser(User user) {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user.getLoginId(), "", new ArrayList<>())); // 공백반환
    }

    @Test
    @DisplayName("안 읽은 알림 수 가져오기")
    void testUnreadNotificationCount() {
        when(notificationRepository.countByReceiverIdAndAlreadyReadFalse(receiver.getId())).thenReturn(3);

        int count = notificationService.notificationUnReadCnt(receiver.getId());

        assertThat(count).isEqualTo(3);
        verify(notificationRepository).countByReceiverIdAndAlreadyReadFalse(receiver.getId());
    }

    @Test
    @DisplayName("알림 생성")
    void testNotificationCreateSuccess() {
        when(userRepository.findById(sender.getId())).thenReturn(Optional.of(sender));
        when(userRepository.findById(receiver.getId())).thenReturn(Optional.of(receiver));
        when(boardRepository.findById(board.getId())).thenReturn(Optional.of(board));
        when(notificationRepository.existsBySenderIdAndReceiverIdAndBoardId(
                sender.getId(), receiver.getId(), board.getId())).thenReturn(false);

        notificationService.notificationCreate(sender.getId(), receiver.getId(), board.getId(), "test");

        verify(notificationRepository).save(any(Notification.class));
    }

    @Test
    @DisplayName("알림 읽음 처리")
    void testNotificationReadSuccess() {
        mockLoginUser(receiver);
        when(notificationRepository.findById(notification.getId())).thenReturn(Optional.of(notification));
        when(userRepository.findByLoginId(receiver.getLoginId())).thenReturn(Optional.of(receiver));

        notificationService.notificationRead(notification.getId());

        assertThat(notification.isAlreadyRead()).isTrue();
    }

    @Test
    @DisplayName("알림 전체 조회")
    void testNotificationFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Notification> page = new PageImpl<>(Collections.singletonList(notification));
        when(notificationRepository.findByReceiverIdOrderByCreatedAtDesc(receiver.getId(), pageable))
                .thenReturn(page);

        Page<NotificationResponse> result = notificationService.notificationFindAll(receiver.getId(), pageable);

        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getMessage()).isEqualTo("test message");
    }
}

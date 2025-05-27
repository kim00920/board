package com.example.boardtest.user.service;

import com.example._Board.error.BusinessException;
import com.example._Board.user.controller.response.DeleteUserResponse;
import com.example._Board.user.domain.DeleteUser;
import com.example._Board.user.repository.DeleteUserRepository;
import com.example._Board.user.service.Impl.DeleteUserServiceImpl;
import com.example.boardtest.factory.DeleteUserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("탈퇴회원 단위 테스트")
public class DeleteUserServiceTest {

    @InjectMocks
    DeleteUserServiceImpl deleteUserService;

    @Mock
    DeleteUserRepository deleteUserRepository;

    DeleteUser deleteUser;

    @BeforeEach
    void setUp() {
        deleteUser = DeleteUserFactory.CreateDeleteUser();
    }

    @Test
    @DisplayName("탈퇴 회원 단건 조회")
    void deleteUserFindOne() {
        Long id = 1L;
        when(deleteUserRepository.findById(id)).thenReturn(Optional.of(deleteUser));

        DeleteUserResponse response = deleteUserService.deleteUserFindOne(id);

        assertThat(response).isNotNull();
        assertThat(response.getLoginId()).isEqualTo("loginId");
        verify(deleteUserRepository).findById(id);
    }

    @Test
    @DisplayName("탈퇴 회원 전체 조회")
    void deleteUserFindAll() {
        when(deleteUserRepository.findAll()).thenReturn(Arrays.asList(deleteUser));

        List<DeleteUserResponse> responseList = deleteUserService.deleteUserFindAll();

        assertThat(responseList).isNotEmpty();
        assertThat(responseList.get(0).getEmail()).isEqualTo("email");
        verify(deleteUserRepository).findAll();
    }

    @Test
    @DisplayName("탈퇴 회원 삭제")
    void deleteUserDelete() {
        Long id = 1L;
        when(deleteUserRepository.findById(id)).thenReturn(Optional.of(deleteUser));

        deleteUserService.deleteUserDelete(id);

        verify(deleteUserRepository).delete(deleteUser);
    }

    @Test
    @DisplayName("탈퇴 회원 삭제 실패")
    void deleteUserDelete_notFound() {
        Long id = 999L;
        when(deleteUserRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(BusinessException.class,
                () -> deleteUserService.deleteUserDelete(id));

        verify(deleteUserRepository, never()).delete(any());
    }
}

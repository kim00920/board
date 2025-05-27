package com.example.boardtest.factory;

import com.example._Board.user.domain.DeleteUser;
import com.example._Board.user.domain.Role;

import java.time.LocalDateTime;

public class DeleteUserFactory {

    public static DeleteUser CreateDeleteUser() {
        return DeleteUser.builder()
                .id(1L)
                .loginId("loginId")
                .email("email")
                .name("name")
                .role(Role.USER.name())
                .deleteAt(LocalDateTime.now())
                .build();
    }
}

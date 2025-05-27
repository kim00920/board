package com.example._Board.user.controller;

import com.example._Board.user.controller.response.DeleteUserResponse;
import com.example._Board.user.service.DeleteUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DeleteUserController {

    private final DeleteUserService deleteUserService;

    // 단건 조회
    @GetMapping("deleteUser/{deleteUserId}")
    @ResponseStatus(HttpStatus.OK)
    public DeleteUserResponse deleteUserFindOne(Long deleteUserId) {
        return deleteUserService.deleteUserFindOne(deleteUserId);
    }

    // 전체 조회
    @GetMapping("deleteUser")
    @ResponseStatus(HttpStatus.OK)
    public List<DeleteUserResponse> deleteUserFindAll() {
        return deleteUserService.deleteUserFindAll();
    }

    // 탈퇴한 회원 삭제
    @DeleteMapping("deleteUser/{deleteUserId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserDelete(@PathVariable("id") Long deleteUserId) {
        deleteUserService.deleteUserDelete(deleteUserId);
    }

}

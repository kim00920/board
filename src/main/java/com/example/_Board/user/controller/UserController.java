package com.example._Board.user.controller;

import com.example._Board.user.controller.request.UserPasswordEditRequest;
import com.example._Board.user.controller.request.UserEditRequest;
import com.example._Board.user.controller.request.UserSignupRequest;
import com.example._Board.user.controller.response.UserResponse;
import com.example._Board.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UserController {

    private final UserService userService;

    // 회원 가입
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void userSignup(@RequestBody @Valid UserSignupRequest request) {
        userService.userSignup(request);
    }

    // 로그인 아이디 중복 체크
    @PostMapping("/user/exist")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void userLoginIdDuplicateCheck(String loginId) {
        userService.loginIdDuplicateCheck(loginId);
    }

    // 내 정보 가져오기
    @GetMapping("/user/myInfo")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse findByDetailMyInfo() {
        return userService.findByMyInfo();
    }

    // 회원 전체 조회하기
    @GetMapping("/user/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> findAllUser() {
        return userService.findAllUser();
    }

    // 회원 수정
    @PutMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public void userEdit(@RequestBody @Valid UserEditRequest request) {
        userService.userEdit(request);
    }

    // 회원 탈퇴
    @DeleteMapping("/user")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void userDelete() {
        userService.userDelete();
    }

    // 회원 비밀번호 변경
    // 회원 탈퇴
    @PutMapping("/user/password")
    @ResponseStatus(HttpStatus.OK)
    public void userEditPassword(@RequestBody UserPasswordEditRequest request) {
        userService.userPasswordEdit(request);
    }

}

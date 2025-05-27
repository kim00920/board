package com.example._Board.board.controller;

import com.example._Board.board.controller.request.CategoryCreateRequest;
import com.example._Board.board.controller.request.CategoryEditRequest;
import com.example._Board.board.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 생성
    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public void categoryCreate(@RequestBody @Valid CategoryCreateRequest categoryCreateRequest) {
        categoryService.categoryCreate(categoryCreateRequest);
    }

    // 카테고리 전체 조회
    @GetMapping("category")
    @ResponseStatus(HttpStatus.OK)
    public void findAllCategory() {
        categoryService.categoryFindAll();
    }

    // 카테고리 수정
    @PutMapping("/category/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public void categoryEdit(@PathVariable("categoryId") Long categoryId,
                             @RequestBody CategoryEditRequest categoryEditRequest) {
        categoryService.categoryEdit(categoryId, categoryEditRequest);
    }

    // 카테고리 삭제
    @DeleteMapping("/category/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public void categoryDelete(@PathVariable("categoryId") Long categoryId) {
        categoryService.categoryDelete(categoryId);
    }
}

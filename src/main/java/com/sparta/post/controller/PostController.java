package com.sparta.post.controller;


import com.sparta.post.dto.PostListResponseDto;
import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.dto.ResponseDto;
import com.sparta.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 목록 조회
    @GetMapping("/api/posts")
    public PostListResponseDto getpostlist(){
        return postService.postlist();
    }
    // 특정 게시글 조회
    @GetMapping("/api/post/{id}")
    public PostResponseDto getpost(@PathVariable Long id){
        return postService.getPost(id);
    }

    // 게시글 작성
    @PostMapping("/api/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest request){

        return postService.createPost(requestDto, request);
    }

    // 게시글 수정
    @PatchMapping ("/api/post/update/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, HttpServletRequest request){
        return postService.updatePost(id, requestDto, request);
    }

    // 게시글 삭제
    @DeleteMapping ("/api/post/{id}")
    public ResponseDto deletePost(@PathVariable Long id, HttpServletRequest request){

        return postService.deletePost(id, request);
    }



}

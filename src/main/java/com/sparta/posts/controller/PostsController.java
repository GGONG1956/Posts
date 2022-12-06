package com.sparta.posts.controller;


import com.sparta.posts.dto.PostsRequestDto;
import com.sparta.posts.dto.PostsResponseDto;
import com.sparta.posts.dto.ResponseDto;
import com.sparta.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    // 게시글 목록 조회
    @GetMapping("/api/posts")
    public List<PostsResponseDto> getpostslist(){
        return postsService.postslist();
    }
    // 특정 게시글 조회
    @GetMapping("/api/post/{id}")
    public PostsResponseDto getposts(@PathVariable Long id){
        return postsService.getPosts(id);
    }

    // 게시글 작성
    @PostMapping("/api/post")
    public PostsResponseDto createPosts(@RequestBody PostsRequestDto requestDto, HttpServletRequest request){

        return postsService.createPosts(requestDto, request);
    }

    // 게시글 수정
    @PatchMapping ("/api/post/update/{id}")
    public PostsResponseDto updatePosts(@PathVariable Long id, @RequestBody PostsRequestDto requestDto, HttpServletRequest request){
        return postsService.updatePosts(id, requestDto, request);
    }

    // 게시글 삭제
    @DeleteMapping ("/api/post/{id}")
    public ResponseDto deletePosts(@PathVariable Long id, HttpServletRequest request){

        return postsService.deletePosts(id, request);
    }



}

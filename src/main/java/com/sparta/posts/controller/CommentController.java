package com.sparta.posts.controller;

import com.sparta.posts.dto.CommentResponseDto;
import com.sparta.posts.dto.PostsCommentRequestDto;
import com.sparta.posts.service.CommentServiece;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiece commentServiece;

    @PostMapping("/api/comment/{id}")
    public CommentResponseDto createPosts(@PathVariable Long id, @RequestBody PostsCommentRequestDto requestDto, HttpServletRequest request){
        return commentServiece.createCommnet(id, requestDto, request);
    }
}

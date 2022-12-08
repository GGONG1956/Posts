package com.sparta.post.controller;

import com.sparta.post.dto.CommentResponseDto;
import com.sparta.post.dto.CommentRequestDto;
import com.sparta.post.dto.ResponseDto;
import com.sparta.post.service.CommentServiece;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CommentController{

    private final CommentServiece commentServiece;

    // 댓글 작성
    @PostMapping("/api/comment/{id}")
    public CommentResponseDto createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest request){
        return commentServiece.createCommnet(id, requestDto, request);
    }

    @PatchMapping("/api/comment/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest request){
        return  commentServiece.updateComment(id, requestDto, request);
    }
    @DeleteMapping("/api/comment/{id}")
    public ResponseDto deleteComment(@PathVariable Long id, HttpServletRequest request){
        return commentServiece.deleteComment(id, request);
    }


}

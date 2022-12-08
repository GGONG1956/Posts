package com.sparta.post.dto;

import com.sparta.post.entity.Comment;
import com.sparta.post.entity.Post;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostListResponseDto extends ResponseDto{
    // 게시글 목록 조회를 위한 Dto

    // list 선언
    List<PostResponseDto> postlist = new ArrayList<>();
    List<CommentResponseDto> commentlist = new ArrayList<>();
    public PostListResponseDto(){

    }


    public PostListResponseDto(List<PostResponseDto> postlist, List<CommentResponseDto> commentlist) {
        super("게시글 목록 조회 성공",200);
        this.postlist = postlist;
        this.commentlist = commentlist;
    }


    // 포스트를 list에 담기 위한 메서드

}

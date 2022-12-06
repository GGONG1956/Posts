package com.sparta.posts.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostsListResponseDto extends ResponseDto{
    // 게시글 목록 조회를 위한 Dto

    // list 선언
    List<PostsResponseDto> postslist = new ArrayList<>();

    public PostsListResponseDto(){
        super("게시글 목록 조회 성공",200);
    }
    // 포스트를 list에 담기 위한 메서드
    public void addPosts(PostsResponseDto responseDto){
        postslist.add(responseDto);
    }
}

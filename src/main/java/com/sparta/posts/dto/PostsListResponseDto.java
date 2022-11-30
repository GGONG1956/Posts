package com.sparta.posts.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostsListResponseDto extends ResponseDto{

    List<PostsResponseDto> postslist = new ArrayList<>();

    public PostsListResponseDto(){
        super("게시글 목록 조회 성공");
    }
    public void addPosts(PostsResponseDto responseDto){
        postslist.add(responseDto);
    }
}

package com.sparta.posts.dto;

import lombok.Getter;


@Getter
public class PostsRequestDto { // 요청을 담는 Dto
    private String username;
    private String contents;
    private String title;
    private String password;
}




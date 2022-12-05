package com.sparta.posts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostsRequestDto { // 요청을 담는 Dto
    private String contents;
    private String title;
}




package com.sparta.posts.dto;

import com.sparta.posts.entity.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsResponseDto {
    private String title;
    private String contents;
    private String username;

    public PostsResponseDto(Posts posts) {
        this.title = posts.getTitle();
        this.contents = posts.getContents();
        this.username = posts.getUsername();
    }
}

package com.sparta.posts.dto;

import com.sparta.posts.entity.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostsResponseDto {

    // 게시글 단순 조회, 게시글 작성에 사용될 Dto
    private Long id;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private String title;
    private String contents;
    private String username;
    public PostsResponseDto(Posts posts, String username) {
        this.id = posts.getId();
        this.createAt = posts.getCreatedAt();
        this.modifiedAt = posts.getModifiedAt();
        this.title = posts.getTitle();
        this.contents = posts.getContents();
        this.username = username;
    }

}

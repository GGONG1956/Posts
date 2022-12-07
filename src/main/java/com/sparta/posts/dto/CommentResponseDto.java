package com.sparta.posts.dto;

import com.sparta.posts.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;

    private String contents;

    private String username;
    public CommentResponseDto(Comment comment, String username) {
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.username = username;
    }
}

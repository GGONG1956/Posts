package com.sparta.post.dto;

import com.sparta.post.entity.Comment;
import com.sparta.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CommentResponseDto{

    private Long id;

    private String contents;

    private String username;


    // 댓글 반환 dto
    public CommentResponseDto(Comment comment, String username) {
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.username = username;

    }


}

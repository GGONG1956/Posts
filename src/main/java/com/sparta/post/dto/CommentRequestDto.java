package com.sparta.post.dto;

import com.sparta.post.entity.Comment;
import com.sparta.post.entity.Post;
import com.sparta.post.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentRequestDto {
    private String contents;

    private User user;

    public Comment toEntity(User user, Post post){
        return Comment.builder()
                .user(user)
                .post(post)
                .username(user.getUsername())
                .contents(contents)
                .build();
    }
}

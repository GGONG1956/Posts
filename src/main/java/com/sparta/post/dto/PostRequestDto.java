package com.sparta.post.dto;

import com.sparta.post.entity.Post;
import com.sparta.post.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostRequestDto { // 요청을 담는 Dto

    private User user;
    private String contents;
    private String title;


  public Post toEntity(User user){
      return Post.builder()
              .user(user)
              .username(user.getUsername())
              .title(title)
              .contents(contents)
              .build();
  }

}




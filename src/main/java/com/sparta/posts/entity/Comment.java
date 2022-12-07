package com.sparta.posts.entity;


import com.sparta.posts.dto.PostsCommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    @Column(nullable = false)
    private String contents;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment (PostsCommentRequestDto postsCommentRequestDto, Post post,String username){
        this.post = post;
        this.username = username;
        this.contents = postsCommentRequestDto.getContents();
    }

}

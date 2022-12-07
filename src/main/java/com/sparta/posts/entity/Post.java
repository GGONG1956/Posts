package com.sparta.posts.entity;

import com.sparta.posts.dto.PostsRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String username;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<Comment>();

    public Post(String contents, String title){
        this.contents = contents;
        this.title = title;
    }

    // 요청 데이터를 위한 생성자
    public Post(PostsRequestDto requestDto, Long userId, String username){
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.userId = userId;
        this.username = username;
    }


    // 게시글 수정을 위한 생성자
    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void update(PostsRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
    }
}
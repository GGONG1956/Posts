package com.sparta.posts.entity;

import com.sparta.posts.dto.PostsRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Posts extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String password;

    public Posts(String username, String contents, String title, String password){
        this.contents = contents;
        this.title = title;
        this.username = username;
        this.password = password;
    }

    // 요청 데이터를 위한 생성자
    public Posts(PostsRequestDto requestDto){
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
    }

    // 게시글 수정을 위한 생성자
    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}

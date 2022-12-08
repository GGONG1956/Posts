package com.sparta.post.entity;

import com.sparta.post.dto.PostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER)
    private List<Comment> commentList = new ArrayList<>();

    // 하 dto 겨우 뻇다 진짜 개짜증나는 빌더 거지같은 빌더
    public Post(String contents, String title,String username, User user){
        this.username = user.getUsername();
        this.contents = contents;
        this.title = title;
        this.user = user;


        user.getPostList().add(this);
    }

    // 요청 데이터를 위한 생성자

    // 게시글 수정을 위한 생성자
    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void update(PostRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
    }
}

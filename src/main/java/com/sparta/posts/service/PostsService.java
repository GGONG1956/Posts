package com.sparta.posts.service;

import com.sparta.posts.dto.PostsRequestDto;
import com.sparta.posts.entity.Posts;
import com.sparta.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Posts createPosts(PostsRequestDto requestDto){
        Posts posts = new Posts(requestDto);
        postsRepository.save(posts);
        return posts;
    }
    @Transactional(readOnly = true)
    public Posts getPosts(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        return posts;
    }
    @Transactional(readOnly = true)
    public List<Posts> postslist() {
        return postsRepository.findAllByOrderByModifiedAtAsc();
    }
    @Transactional
    public Long updatePosts(Long id, PostsRequestDto requestDto){

        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        if (posts.getPassword().equals(requestDto.getPassword())){
            posts.update(requestDto.getTitle(), requestDto.getContents());
        }else {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        return posts.getId();
    }
    @Transactional
    public String deletePosts(Long id, PostsRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        if (posts.getPassword().equals(requestDto.getPassword())){
            postsRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        return "삭제 성공!";
    }



    //   public boolean checkPw(String password, Long id) throws Exception{
 //       Posts posts = postsRepository.findById(id).orElseThrow();
  //  }

}

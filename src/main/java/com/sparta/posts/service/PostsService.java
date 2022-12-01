package com.sparta.posts.service;

import com.sparta.posts.dto.PostsListResponseDto;
import com.sparta.posts.dto.PostsRequestDto;
import com.sparta.posts.dto.PostsResponseDto;
import com.sparta.posts.dto.ResponseDto;
import com.sparta.posts.entity.Posts;
import com.sparta.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    // 게시글 작성
    @Transactional
    public PostsResponseDto createPosts(PostsRequestDto requestDto){
        Posts posts = new Posts(requestDto);
        postsRepository.save(posts);
        return new PostsResponseDto(posts);
 }

    // 특정 게시글 조회
    @Transactional(readOnly = true)
    public PostsResponseDto getPosts(Long id) {
      Posts posts = postsRepository.findById(id).orElseThrow(
              () -> new NullPointerException("게시글이 존재하지 않습니다.")
      );
      return new PostsResponseDto(posts);
    }

    // 게시글 목록 전체 조회
    @Transactional(readOnly = true)
    public List<PostsResponseDto> postslist() {

        List<PostsResponseDto> postlist = new ArrayList<>();

        List<Posts> postsList = postsRepository.findAllByOrderByModifiedAtAsc();

        for(Posts posts : postsList){
            postlist.add(new PostsResponseDto(posts));
        }
        return postlist;
    }

    // 게시글 수정
    @Transactional
    public PostsResponseDto updatePosts(Long id, PostsRequestDto requestDto){

        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        if (checkPw(id, requestDto)){

            posts.update(requestDto.getTitle(), requestDto.getContents());

        }else {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        return new PostsResponseDto(posts);
    }

    // 게시글 삭제
    @Transactional
    public ResponseDto deletePosts(Long id, PostsRequestDto requestDto) {

        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        if (checkPw(id, requestDto)){
            postsRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        return new ResponseDto("삭제 성공!");
    }


    // 비밀번호 검사를 위한 메서드
    @Transactional
    public boolean checkPw(Long id, PostsRequestDto requestDto) {

        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        if (posts.getPassword().equals(requestDto.getPassword())) {
            return true;
        } else {
            return false;
        }
    }


}

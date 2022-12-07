package com.sparta.posts.service;

import com.sparta.posts.dto.*;
import com.sparta.posts.entity.Post;
import com.sparta.posts.entity.User;
import com.sparta.posts.jwt.JwtUtil;
import com.sparta.posts.repository.PostsRepository;
import com.sparta.posts.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    // 게시글 작성
    @Transactional
    public PostsResponseDto createPosts(PostsRequestDto requestDto, HttpServletRequest request) {

        // 웹에서 토큰 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            System.out.println(requestDto.getContents());
            // 요청받은 DTO 로 DB에 저장할 객체 만들기
            Post post = postsRepository.saveAndFlush(new Post(requestDto, user.getId(),user.getUsername()));

            return new PostsResponseDto(post, user.getUsername());
        } else {
            return null;
        }
    }

    // 특정 게시글 조회
    @Transactional(readOnly = true)
    public PostsResponseDto getPosts(Long id) {
        Post post = postsRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시글이 존재하지 않습니다.")
        );
        Long userId = post.getUserId();
        return new PostsResponseDto(post, post.getUsername());
    }

    // 게시글 목록 전체 조회
    @Transactional(readOnly = true)
    public List<PostsResponseDto> postslist() {

        List<PostsResponseDto> postlist = new ArrayList<>();

        List<Post> postsList = postsRepository.findAllByOrderByModifiedAtAsc();

        for (Post post : postsList) {
            postlist.add(new PostsResponseDto(post, post.getUsername()));
        }
        return postlist;
    }

    // 게시글 수정
    @Transactional
    public PostsResponseDto updatePosts(Long id, PostsRequestDto requestDto, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 최저가 업데이트 가능
        if (token != null) {


            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Post post = postsRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new NullPointerException("사용자가 일치하지 않습니다.")
            );
                post.update(requestDto);


            return new PostsResponseDto(post, post.getUsername());

        } else {
            return null;
        }

    }

    // 게시글 삭제
    @Transactional
    public ResponseDto deletePosts(Long id, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Post posts = postsRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new NullPointerException("해당 게시글은 존재하지 않습니다.")
            );

                postsRepository.deleteById(id);


            return new ResponseDto("삭제 성공", 200);
        } else {
            return null;
        }



    }
}



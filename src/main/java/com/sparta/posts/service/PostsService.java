package com.sparta.posts.service;

import com.sparta.posts.dto.*;
import com.sparta.posts.entity.Posts;
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
import java.util.Optional;

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
            Posts posts = postsRepository.saveAndFlush(new Posts(requestDto, user.getId(),user.getUsername()));

            return new PostsResponseDto(posts, user.getUsername());
        } else {
            return null;
        }
    }

    // 특정 게시글 조회
    @Transactional(readOnly = true)
    public PostsResponseDto getPosts(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시글이 존재하지 않습니다.")
        );
        Long userId = posts.getUserId();
        return new PostsResponseDto(posts, posts.getUsername());
    }

    // 게시글 목록 전체 조회
    @Transactional(readOnly = true)
    public List<PostsResponseDto> postslist() {

        List<PostsResponseDto> postlist = new ArrayList<>();

        List<Posts> postsList = postsRepository.findAllByOrderByModifiedAtAsc();

        for (Posts posts : postsList) {
            postlist.add(new PostsResponseDto(posts, posts.getUsername()));
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

            Posts posts = postsRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new NullPointerException("사용자가 일치하지 않습니다.")
            );
                posts.update(requestDto);


            return new PostsResponseDto(posts, posts.getUsername());

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

            Posts posts = postsRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new NullPointerException("해당 게시글은 존재하지 않습니다.")
            );

                postsRepository.deleteById(id);


            return new ResponseDto("삭제 성공", 200);
        } else {
            return null;
        }



    }
}



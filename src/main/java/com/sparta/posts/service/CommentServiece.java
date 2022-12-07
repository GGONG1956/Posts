package com.sparta.posts.service;

import com.sparta.posts.dto.CommentResponseDto;
import com.sparta.posts.dto.PostsCommentRequestDto;
import com.sparta.posts.entity.Comment;
import com.sparta.posts.entity.Post;
import com.sparta.posts.entity.User;
import com.sparta.posts.jwt.JwtUtil;
import com.sparta.posts.repository.CommentRepository;
import com.sparta.posts.repository.PostsRepository;
import com.sparta.posts.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentServiece {
    private final CommentRepository commentRepository;

    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;
    // 댓글 작성
    public CommentResponseDto createCommnet(Long id, PostsCommentRequestDto requestDto, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        Post post = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

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
            Comment comment = commentRepository.save(new Comment(requestDto,post,user.getUsername()));
            System.out.println(comment.getPost());
            System.out.println(comment.getContents());
            return new CommentResponseDto(comment, user.getUsername());
        } else {
            return null;
        }

    }
}

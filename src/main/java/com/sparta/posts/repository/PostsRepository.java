package com.sparta.posts.repository;

import com.sparta.posts.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;


public interface PostsRepository extends JpaRepository<Post, Long> { // 게시글 Repository


    // 리스트를 뽑아오는 Repository
    List<Post> findAllByOrderByModifiedAtAsc();


    Optional<Post> findByIdAndUserId(Long id, Long id1);
}

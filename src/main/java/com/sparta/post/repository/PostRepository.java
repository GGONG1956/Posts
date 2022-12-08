package com.sparta.post.repository;

import com.sparta.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> { // 게시글 Repository


    // 리스트를 뽑아오는 Repository
    List<Post> findAllByOrderByModifiedAtAsc();


    Optional<Post> findByIdAndUserId(Long id, Long id1);
}

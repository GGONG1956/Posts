package com.sparta.post.repository;

import com.sparta.post.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByOrderByModifiedAtAsc();

    Comment saveAndFlush(Comment comment);

    Optional<Comment> findByIdAndUserId(Long id, Long id1);
}

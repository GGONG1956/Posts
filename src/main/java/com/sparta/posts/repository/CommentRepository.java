package com.sparta.posts.repository;

import com.sparta.posts.entity.Comment;
import com.sparta.posts.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment saveAndFlush(Comment comment);
}

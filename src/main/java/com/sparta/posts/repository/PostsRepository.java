package com.sparta.posts.repository;

import com.sparta.posts.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface PostsRepository extends JpaRepository<Posts, Long> {

    List<Posts> findAllByOrderByModifiedAtAsc();


}
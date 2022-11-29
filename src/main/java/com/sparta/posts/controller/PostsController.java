package com.sparta.posts.controller;

import com.sparta.posts.dto.PostsRequestDto;
import com.sparta.posts.entity.Posts;
import com.sparta.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @GetMapping("/")
    public ModelAndView home(){
        return new ModelAndView("index");
    }

    @GetMapping("/api/postslist")
    public List<Posts> getpostslist(){
        return postsService.postslist();
    }

    @PostMapping("/api/posts")
    public Posts createPosts(@RequestBody PostsRequestDto requestDto){
        return postsService.createPosts(requestDto);
    }

    @GetMapping("/api/posts/{id}")
    public Posts getposts(@PathVariable Long id){
        return postsService.getPosts(id);
    }

    @PutMapping ("/api/posts/{id}")
    public long updatePosts(@PathVariable Long id, @RequestBody PostsRequestDto requestDto){
        return postsService.updatePosts(id, requestDto);
    }
    @DeleteMapping ("/api/posts/{id}")
    public String deletePosts(@PathVariable Long id, @RequestBody PostsRequestDto requestDto){

        return postsService.deletePosts(id, requestDto);
    }


}

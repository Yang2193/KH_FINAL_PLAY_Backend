package com.kh.finalPlayTime.service;


import com.kh.finalPlayTime.dto.PostDto;
import com.kh.finalPlayTime.entity.Post;
import com.kh.finalPlayTime.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor


public class PostService {
    private final PostRepository postRepository;

    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return convertToDtoList(posts);
    }

    private List<PostDto> convertToDtoList(List<Post> posts) {
        List<PostDto> dtoList = new ArrayList<>();
        for (Post post : posts) {
            PostDto postDto = new PostDto();
            postDto.setId(post.getId());
            postDto.setMemberInfo(post.getMemberInfo());
            postDto.setPostTitle(post.getPostTitle());
            postDto.setPostContent(post.getPostContent());
            postDto.setPostImageUrl(post.getPostImageUrl());
            postDto.setPostCategory(post.getPostCategory());
            postDto.setPostViews(post.getPostViews());
            postDto.setPostDate(post.getPostDate());
            dtoList.add(postDto);
        }
        return dtoList;
    }
    public PostDto getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            return convertToDto(post);
        }
        return null;
    }

    private PostDto convertToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setMemberInfo(post.getMemberInfo());
        postDto.setPostTitle(post.getPostTitle());
        postDto.setPostContent(post.getPostContent());
        postDto.setPostImageUrl(post.getPostImageUrl());
        postDto.setPostCategory(post.getPostCategory());
        postDto.setPostViews(post.getPostViews());
        postDto.setPostDate(post.getPostDate());
        return postDto;
    }

    public List<PostDto> getMemberPosts(String userId) {
        List<Post> posts = postRepository.findByMemberInfoUserId(userId);
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            PostDto postDto = new PostDto();
            postDto.setId(post.getId());
            postDto.setMemberInfo(post.getMemberInfo());
            postDto.setPostTitle(post.getPostTitle());
            postDto.setPostContent(post.getPostContent());
            postDto.setPostImageUrl(post.getPostImageUrl());
            postDto.setPostCategory(post.getPostCategory());
            postDto.setPostViews(post.getPostViews());
            postDto.setPostDate(post.getPostDate());
            postDtos.add(postDto);
        }
        return postDtos;
    }

}
package com.kh.finalPlayTime.service;

import com.kh.finalPlayTime.dto.PostDto;
import com.kh.finalPlayTime.entity.MemberInfo;
import com.kh.finalPlayTime.entity.Post;
import com.kh.finalPlayTime.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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
            PostDto postDto = convertToDto(post);
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

    public void increasePostViews(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            post.setPostViews(post.getPostViews() + 1);
        }
    }

    public PostDto addPost(PostDto postDto) {
        Post post = convertToPost(postDto);
        post.setPostDate(LocalDateTime.now()); // 현재 시간 설정

        // 변경: MemberInfo 객체 생성 및 설정
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setUserId(postDto.getUserId());
        post.setMemberInfo(memberInfo);

        Post savedPost = postRepository.save(post);
        return convertToDto(savedPost);
    }

    private Post convertToPost(PostDto postDto) {
        Post post = new Post();
        post.setPostTitle(postDto.getPostTitle());
        post.setPostContent(postDto.getPostContent());
        post.setPostImageUrl(postDto.getPostImageUrl());
        post.setPostCategory(postDto.getPostCategory());
        post.setPostViews(postDto.getPostViews());
        return post;
    }

    private PostDto convertToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setPostTitle(post.getPostTitle());
        postDto.setPostContent(post.getPostContent());
        postDto.setPostImageUrl(post.getPostImageUrl());
        postDto.setPostCategory(post.getPostCategory());
        postDto.setPostViews(post.getPostViews());
        postDto.setPostDate(post.getPostDate());
        return postDto;
    }
}

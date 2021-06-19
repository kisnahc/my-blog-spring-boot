package com.project.myblog.api;

import com.project.myblog.domain.board.Post;
import com.project.myblog.dto.CreatePostRequestDto;
import com.project.myblog.dto.CreatePostResponseDto;
import com.project.myblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/api/posts")
    public CreatePostResponseDto createPost(@RequestBody @Valid CreatePostRequestDto requestDto) {
        Long postId = postService.createPost(requestDto);
        return new CreatePostResponseDto(postId, requestDto.getTitle(), requestDto.getAuthorEmail(), requestDto.getContent());

    }
}

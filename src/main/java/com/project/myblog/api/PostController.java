package com.project.myblog.api;

import com.project.myblog.domain.board.Board;
import com.project.myblog.domain.board.Post;
import com.project.myblog.domain.postDto.*;
import com.project.myblog.service.PostService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/api/posts")
    public CreatePostResponseDto createPost(@RequestBody @Valid CreatePostRequestDto requestDto) {
        Long postId = postService.createPost(requestDto);
        return new CreatePostResponseDto(postId,
                requestDto.getTitle(),
                requestDto.getAuthor(),
                requestDto.getContent(),
                LocalDateTime.now());
    }

    @GetMapping("/api/posts")
    public Result postList() {
        List<Post> postList = postService.PostList();
        List<FindPostResponseDto> collect = postList.stream()
                .map(p -> new FindPostResponseDto(p.getTitle(), p.getAuthor().getEmail(), LocalDateTime.now()))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    @GetMapping("/api/posts/title")
    public FindPostResponseDto findPostByTitle(@RequestBody @Valid FindPostByTitleRequestDto requestDto) {
        Post findPost = postService.findPostByTitle(requestDto);
        return new FindPostResponseDto(findPost.getTitle(), findPost.getAuthor().getEmail(), LocalDateTime.now());
    }

    @PatchMapping("/api/posts/{id}")
    public UpdatePostResponseDto updatePost(@PathVariable("id") Long id,
                                            @RequestBody @Valid UpdatePostRequestDto requestDto) {
        Post findPost = postService.findById(id);
        postService.updatePost(findPost.getId(), requestDto.getTitle(), requestDto.getContent());
        return new UpdatePostResponseDto(findPost.getId(), requestDto.getTitle(), requestDto.getContent(), LocalDateTime.now());

    }


    @Data
    public class Result<T> {
        private int count;
        private T data;

        public Result(int count, T data) {
            this.count = count;
            this.data = data;
        }
    }
}

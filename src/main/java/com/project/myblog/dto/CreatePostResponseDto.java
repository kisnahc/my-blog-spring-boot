package com.project.myblog.dto;

import com.project.myblog.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostResponseDto {

    private Long postId;
    private String title;
    private String author;
    private String content;

    public CreatePostResponseDto(Long postId, String title, String author, String content) {
        this.postId = postId;
        this.title = title;
        this.author = author;
        this.content = content;
    }
}

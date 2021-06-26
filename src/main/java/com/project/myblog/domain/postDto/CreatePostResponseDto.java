package com.project.myblog.domain.postDto;

import com.project.myblog.domain.board.Board;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreatePostResponseDto {

    private Long postId;
    private String title;
    private String author;
    private String content;
    private LocalDateTime createDate;

    public CreatePostResponseDto(Long postId, String title, String author, String content, LocalDateTime createDate) {
        this.postId = postId;
        this.title = title;
        this.author = author;
        this.content = content;
        this.createDate = createDate;
    }
}

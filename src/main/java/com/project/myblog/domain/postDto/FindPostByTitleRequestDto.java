package com.project.myblog.domain.postDto;

import com.project.myblog.domain.board.Post;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class FindPostByTitleRequestDto {

    @NotEmpty
    private String title;

    public Post toFindPost() {
        return Post.builder()
                .title(getTitle())
                .build();
    }

}

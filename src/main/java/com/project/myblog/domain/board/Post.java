package com.project.myblog.domain.board;

import com.project.myblog.domain.BaseTimeEntity;
import com.project.myblog.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @NotEmpty
    private String title;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member author;

    @Lob
    @NotEmpty
    private String content;


    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public void lastModifiedPost(String title, String content) {
        this.title = title;
        this.content = content;
        this.getLastModifiedDate();
    }

    @Builder
    public Post(String title, Member author, String content, List<Comment> commentList, Board board) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.commentList = commentList;
        this.board = board;
    }
}

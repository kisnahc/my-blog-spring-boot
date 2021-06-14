package com.project.myblog.domain.board;

import com.project.myblog.domain.BaseTimeEntity;
import com.project.myblog.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member author;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    public void lastModifiedPost(String content) {
        this.content = content;
        this.getLastModifiedDate();
    }

    @Builder
    public Post(Member author, String content, Board board, List<Comment> commentList) {
        this.author = author;
        this.content = content;
        this.board = board;
        this.commentList = commentList;
    }
}

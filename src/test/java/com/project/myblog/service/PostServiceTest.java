package com.project.myblog.service;

import com.project.myblog.domain.Member;
import com.project.myblog.domain.board.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    private PostService postService;
    @Autowired
    private MemberService memberService;


    @Test
    @Rollback(value = false)
    public void createPost() throws Exception {
        //given
        Member member = Member.builder()
                .email("1@naver.com")
                .username("kang")
                .age(30)
                .build();

        memberService.join(member);

        Post post = Post.builder()
                .title("title spring")
                .author(member)
                .content("Hello Spring")
                .board(null)
                .build();

        Long savedId = postService.createPost(post);

        //when
        Post findPost = postService.findPost(savedId);

        //then
        Assertions.assertThat(findPost.getId()).isEqualTo(post.getId());
        Assertions.assertThat(findPost.getAuthor()).isEqualTo(post.getAuthor());
    }

    @Test
    public void postList() throws Exception {
        //given
        Member member = Member.builder()
                .email("1@naver.com")
                .username("kang")
                .age(30)
                .build();

        memberService.join(member);

        Post post1 = Post.builder()
                .title("title spring")
                .author(member)
                .content("Hello Spring")
                .board(null)
                .build();

        Post post2 = Post.builder()
                .title("title jpa")
                .author(member)
                .content("Hello Jpa")
                .board(null)
                .build();

        postService.createPost(post1);
        postService.createPost(post2);

        //when
        List<Post> postList = postService.PostList();

        //then
        Assertions.assertThat(postList.size()).isEqualTo(2);
    }

    @Test
    @Rollback(value = false)
    public void updatePost() throws Exception {
        //given
        Member member = Member.builder()
                .email("1@naver.com")
                .username("kang")
                .age(30)
                .build();

        memberService.join(member);

        Post post = Post.builder()
                .title("title spring")
                .author(member)
                .content("Hello Spring")
                .board(null)
                .build();

        Long savedId = postService.createPost(post);

        //when
        postService.updatePost(savedId, "jpa!!!!!", "jpa is ...");

    }
}
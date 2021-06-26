package com.project.myblog.service;

import com.project.myblog.domain.board.Post;
import com.project.myblog.domain.memberDto.CreateMemberRequestDto;
import com.project.myblog.domain.postDto.CreatePostRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;



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

        CreateMemberRequestDto memberA = getCreateMember("spring@naver.com", "kang", 30);

        memberService.join(memberA);

        CreatePostRequestDto post = getCreatePost("title spring", "spring@naver.com", "Hello Spring");

        Long savedId = postService.createPost(post);

        //when
        Post findPost = postService.findById(savedId);

        //then
        Assertions.assertThat(findPost.getAuthor().getEmail()).isEqualTo(post.getAuthor());
    }

    @Test
    @Rollback(value = false)
    public void updatePost() throws Exception {
        //given
        CreateMemberRequestDto memberA = getCreateMember("1@naver.com", "kang", 30);
        memberService.join(memberA);

        CreatePostRequestDto postA = getCreatePost("POST A", "1@naver.com", "content is ....");
        Long postId = postService.createPost(postA);
        Post findPost = postService.findById(postId);

        //when
        postService.updatePost(findPost.getId(), "POST B", "spring is ...");

        //then
        Assertions.assertThat(findPost.getTitle()).isEqualTo("POST B");
    }


    private CreateMemberRequestDto getCreateMember(String email, String username, int age) {
        CreateMemberRequestDto requestDto = new CreateMemberRequestDto();
        requestDto.setEmail(email);
        requestDto.setUsername(username);
        requestDto.setAge(age);
        return requestDto;
    }

    private CreatePostRequestDto getCreatePost(String title, String author, String content) {
        CreatePostRequestDto requestDto = new CreatePostRequestDto();
        requestDto.setTitle(title);
        requestDto.setAuthor(author);
        requestDto.setContent(content);
        return requestDto;
    }
}
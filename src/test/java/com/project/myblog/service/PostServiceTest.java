package com.project.myblog.service;

import com.project.myblog.domain.board.Post;
import com.project.myblog.dto.CreateMemberRequestDto;
import com.project.myblog.dto.CreatePostRequestDto;
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

        CreateMemberRequestDto memberA = getCreateMemberRequestDto("spring@naver.com", "kang", 30);

        memberService.join(memberA);

        CreatePostRequestDto post = getCreatePost("title spring", "spring@naver.com", "Hello Spring");

        Long savedId = postService.createPost(post);

        //when
        Post findPost = postService.findPost(savedId);

        //then
        Assertions.assertThat(findPost.getAuthor().getEmail()).isEqualTo(post.getAuthorEmail());
    }


    private CreateMemberRequestDto getCreateMemberRequestDto(String email, String username, int age) {
        CreateMemberRequestDto requestDto = new CreateMemberRequestDto();
        requestDto.setEmail(email);
        requestDto.setUsername(username);
        requestDto.setAge(age);
        return requestDto;
    }

    private CreatePostRequestDto getCreatePost(String title, String author, String content) {
        CreatePostRequestDto requestDto = new CreatePostRequestDto();
        requestDto.setTitle(title);
        requestDto.setAuthorEmail(author);
        requestDto.setContent(content);
        return requestDto;
    }
}
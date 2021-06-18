package com.project.myblog.domain.board;

import com.project.myblog.domain.Member;
import com.project.myblog.repository.MemberRepository;
import com.project.myblog.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
@Rollback(value = false)
class PostTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    public void testPost() throws Exception {
        //given

        Member memberA = Member.builder()
                .email("spring@naver.com")
                .username("memberA")
                .age(30)
                .build();

        memberRepository.save(memberA);

        Post post = Post.builder()
                .title("hello jpa")
                .author(memberA)
                .content("hello spring")
                .commentList(null)
                .build();

        Post savePost = postRepository.save(post);

        //when
        Post findPost = postRepository.findById(savePost.getId()).get();

        Thread.sleep(2000);

        findPost.lastModifiedPost("hello spring data jpa", "helloJpa");

        //then
        Assertions.assertThat(findPost.getId()).isEqualTo(post.getId());
    }

}
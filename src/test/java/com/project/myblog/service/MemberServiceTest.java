package com.project.myblog.service;

import com.project.myblog.domain.Member;
import com.project.myblog.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @Test
    public void 회원가입() throws Exception {
        //given
        Optional<Member> member = Optional.ofNullable(Member.builder()
                .email("spring@naver.com")
                .username("memberA")
                .age(30)
                .build());

        //when
        Long savedId = memberService.join(member.get());

        //then
        assertThat(member).isEqualTo(memberRepository.findById(savedId));
        assertEquals(member, memberRepository.findById(savedId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member memberA = Member.builder()
                .email("spring@naver.com")
                .username("memberA")
                .age(30)
                .build();

        Member memberB = Member.builder()
                .email("spring@naver.com")
                .username("memberB")
                .age(30)
                .build();

        //when
        memberService.join(memberA);

        //then
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> memberService.join(memberB));
        assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());
    }

    @Test
    @Rollback(value = false)
    public void 회원_전체_조회() throws Exception {
        //given
        Member memberA = Member.builder()
                .email("spring@naver.com")
                .username("memberA")
                .age(30)
                .build();

        Member memberB = Member.builder()
                .email("spring@gmail.com")
                .username("memberB")
                .age(30)
                .build();

        memberService.join(memberA);
        memberService.join(memberB);

        //when
        List<Member> members = memberService.findMembers();

        //then

        assertThat(members.size()).isEqualTo(2);
    }

    @Test
    public void 회원_조회() throws Exception {
        //given
        Member memberA = Member.builder()
                .email("spring@naver.com")
                .username("memberA")
                .age(30)
                .build();

        memberService.join(memberA);

        //when
        Member findMember = memberService.findOne(memberA.getId());

        //then
        assertThat(findMember.getId()).isEqualTo(memberA.getId());
    }

}
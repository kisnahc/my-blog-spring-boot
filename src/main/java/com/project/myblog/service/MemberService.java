package com.project.myblog.service;

import com.project.myblog.domain.Member;
import com.project.myblog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PostUpdate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원_전체_조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원_조회
     */
    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId).get();
    }


    @Transactional
    public void updateMember(Long id, String email) {
        Member member = memberRepository.findById(id).get();
        validateDuplicateMember(member);
        member.lastModifiedEmail(email);
    }
}

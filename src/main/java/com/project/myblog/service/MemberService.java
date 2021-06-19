package com.project.myblog.service;

import com.project.myblog.domain.Member;
import com.project.myblog.dto.CreateMemberRequestDto;
import com.project.myblog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(CreateMemberRequestDto requestDto) {
        validateDuplicateMember(requestDto.getEmail());
        Member savedMember = memberRepository.save(requestDto.toMember());
        return savedMember.getId();
    }

    private void validateDuplicateMember(String email) {
        Optional<Member> findMembers = memberRepository.findByEmail(email);
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
        validateDuplicateMember(email);
        member.lastModifiedEmail(email);
    }
}

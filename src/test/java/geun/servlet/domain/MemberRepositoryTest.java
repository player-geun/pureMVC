package geun.servlet.domain;

import geun.servlet.domain.member.Member;
import geun.servlet.domain.member.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {      // 테스트는 실행 순서가 보장되지 않아서
        memberRepository.clearStore();
    }

    @Test
    void save() {
        // Given
        Member member = new Member("hello", 20);

        // When
        Member savedMember = memberRepository.save(member);

        // Then
        Member findMember = memberRepository.findById(savedMember.getId());

        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() {
        // Given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        // When
        List<Member> result = memberRepository.findAll();

        // Then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }
}
package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

//    MemberService memberService = new MemberServiceImpl(memberRepository); // 의존관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점 발생 (MemberService , MemberServiceImpl)
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appconfig = new AppConfig();
        memberService = appconfig.memberService();
    }
    @Test
    void join() {
        //given - ~가 주어지고
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when - ~했을 때
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());
//        Member findMember = memberService.findMember(2L);

        //then - ~된다.
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}

package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

/**
 * 순수한 Java Test코드
 */
public class MemberTestApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl(); // 다형성 객체생성
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());
        System.out.println("findMember = " + findMember.getName());
        System.out.println("member = " + member.getName());

    }
}

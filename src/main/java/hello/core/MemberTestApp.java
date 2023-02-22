package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 순수한 Java Test코드
 */
public class MemberTestApp {
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl(memberRepository); // ver1. 다형성 객체생성
//        AppConfig appConfig = new AppConfig(); // ver2. 설정파일로부터 로딩
//        MemberService memberService = appConfig.memberService();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // ver3. Spring 컨테이너로부터 꺼내오기
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());
        System.out.println("findMember = " + findMember.getName());
        System.out.println("member = " + member.getName());

    }
}

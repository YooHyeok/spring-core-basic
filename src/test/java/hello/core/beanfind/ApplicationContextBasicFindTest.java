package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 스프링 빈 조회 테스트 클래스
 * 빈 이름으로 조회
 * 빈 타입으로 조회
 */
public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class); //인터페이스로 조회시 구현체가 대상이 된다.
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByType2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class); //구체 타입으로 조회하면 유연성이 떨어진다.
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNameX() {
//        MemberService xxxxx = ac.getBean("xxxxx", MemberService.class);
        /**
         * 예외 테스트
         */
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, // 2번째 인자를 실행하여 1번째 인자의 예외가 발생했을때 성공한다.
                          () -> ac.getBean("xxxxx",MemberService.class)); // 2번째 인자를 실행하여 NoSuchBeanDefinitionException이 터지면 성공(녹색불)
    }
}

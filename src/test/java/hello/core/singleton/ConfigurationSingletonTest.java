package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class); //MemberServiceImpl 인스턴스 생성
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class); //구체타입으로 꺼내는 이유는 구체타입에만 존재하는 메소드 호출하려고.
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);
        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        /**
         * 클래스 정보 주소값 : $$EnhancerBySpringCGLIB$$
         * 스프링 빈의 CGLIB 라는 바이트코드 조작 라이브러리를 통해 AppConfig 클래스를 상속받은 다른클래스를 만들고 스프링 빈으로 등록한것
         * 이렇게 생성,등록된 임의의 다른 클래스가 싱글톤이 보장되도록 해준다.
         */
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean = " + bean.getClass());


    }
}

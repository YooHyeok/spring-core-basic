package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 순수한 Java Test코드
 */
public class OrderTestApp {
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl(memberRepository); // Ver2
//        OrderService orderService = new OrderServiceImpl(memberRepository, discountPolicy);
//        AppConfig appConfig = new AppConfig(); // Ver2
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        /**
         * ApplicationContext : 스프링 컨테이너
         * 기존 AppConfing 설정파일은 개발자가 직접 객체를 생성하여 DI를 했지만, 해당 파일을 스프링 컨테이너의 설정파일로 등록한다.
         * 설정파일에 @Bean으로 등록된 빈을 스프링 컨테이너에 등록이되고, 등록된 빈을 꺼내어 사용한다.
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // Ver3
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); // MemberServiceImpl 인스턴스가 생성된다.
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class); // orderServiceImpl 인스턴스가 생성된다.
        Long yjhId = 1L;
        Long ngcId = 2L;
        Member yjh = new Member(yjhId, "유재혁", Grade.VIP);
        Member ngc = new Member(ngcId, "남궁찬", Grade.BASIC);
        memberService.join(yjh);
        memberService.join(ngc);
        Order sqld = orderService.createOrder(yjh.getId(), "SQLD 개발자", 27000);
        Order orm = orderService.createOrder(ngc.getId(), "ORM 표준 프로그래밍", 37000);
        System.out.println("sqld = " + sqld);
        System.out.println("sqld.calculatePrice() = " + sqld.calculatePrice());
        System.out.println("orm = " + orm);
        System.out.println("orm.calculatorPrice() = " + orm.calculatePrice());


    }
}

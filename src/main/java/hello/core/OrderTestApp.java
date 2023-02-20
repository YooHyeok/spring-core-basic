package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
/**
 * 순수한 Java Test코드
 */
public class OrderTestApp {
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl(memberRepository);
//        OrderService orderService = new OrderServiceImpl(memberRepository, discountPolicy);
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();
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

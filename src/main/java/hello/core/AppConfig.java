package hello.core;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
 * 의존 객체 주입 클래스.
 * 애플리케이션의 실제 동작에 필요한 "구현 객체를 생성" 한다.
 * MemberServiceImpl, MemoryMemberRepository, OrderServiceImpl, RateDiscountPolicy
 */
public class AppConfig {
    public MemberService memberService() { // 다형성 : MemberServiceImpl 클래스는 MeberService 인터페이스를 상속받았다.
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
    }
}

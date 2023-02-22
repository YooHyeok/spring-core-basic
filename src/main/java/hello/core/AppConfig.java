package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 의존 객체 주입 클래스.
 * 애플리케이션의 실제 동작에 필요한 "구현 객체를 생성" 한다.
 * MemberServiceImpl, MemoryMemberRepository, OrderServiceImpl, RateDiscountPolicy
 */
@Configuration
public class AppConfig {

    /**
     * MemberReopsotiry 역할 화 리팩토링 및 중복제거
     * 객체 반환 메소드 생성
     * @return MemoryMemberRepository
     */
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    /**
     * DiscountPolicy 역할 화 리팩토링
     * 객체 반환 메소드 생성
     * @return Discount 할인정책 객체 반환 
     */
    @Bean(name = "discountPolicy") // Bean객체 이름 변경 가능
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy(); // 고정
        return new RateDiscountPolicy();
    }
    
    /**
     * DIP
     * MemberService 구현 객체 반환
     * @return MemoryDB를 주입한 MemberServiceImpl 객체 반환
     */
    @Bean("memberService") // Bean객체 이름 변경 가능
    public MemberService memberService() { // 다형성 : MemberServiceImpl 클래스는 MeberService 인터페이스를 상속받았다.
//        return new MemberServiceImpl(new MemoryMemberRepository());
        return new MemberServiceImpl(memberRepository());
    }

    /**
     * DIP
     * OrderService 구현 객체 반환
     * @return MemoryDB, 할인정책 객체를 주입한 OrderServiceImpl 객체 반환
     */
    @Bean
    public OrderService orderService() {
//        return new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}

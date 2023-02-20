package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
//    MemberService memberService = new MemberServiceImpl(memberRepository);
//    OrderService orderService = new OrderServiceImpl(memberRepository, discountPolicy);
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId,"멤버1", Grade.VIP);
        memberService.join(member);
        Order book1 = orderService.createOrder(member.getId(), "책1", 2500);
        System.out.println("book1 = " + book1);
        System.out.println("book1.calculatePrice() = " + book1.calculatePrice());
        Assertions.assertThat(book1.getDiscountPrice()).isEqualTo(250);


    }
}

package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId,"멤버1", Grade.VIP);
        memberService.join(member);
        Order book1 = orderService.createOrder(member.getId(), "책1", 2500);
        System.out.println("book1 = " + book1);
        System.out.println("book1.calculatePrice() = " + book1.calculatePrice());
        Assertions.assertThat(book1.getDiscountPrice()).isEqualTo(1000);


    }
}

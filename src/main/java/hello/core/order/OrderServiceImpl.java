package hello.core.order;


import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository = new MemoryMemberRepository(); //MemoryDB DI 주입
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //고정할인정책 DI 주입
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); //정률할인정책 DI 주입
    private DiscountPolicy discountPolicy; // interface 추상화에만 의존

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //회원정보 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인 금액
        return new Order(memberId, itemName, itemPrice, discountPrice); // 주문 정보 반환
    }
}

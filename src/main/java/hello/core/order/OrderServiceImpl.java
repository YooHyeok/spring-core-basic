package hello.core.order;


import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
//    private final MemberRepository memberRepository = new MemoryMemberRepository(); //MemoryDB DI 주입
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //고정할인정책 DI 주입
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); //정률할인정책 DI 주입
    private final MemberRepository memberRepository; //MemoryDB DI 주입
    private final DiscountPolicy discountPolicy; // interface 추상화에만 의존

     @Autowired //@Component - AutoAppConfig의 @ComponentSacn을 위해 선언
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //회원정보 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인 금액
        return new Order(memberId, itemName, itemPrice, discountPrice); // 주문 정보 반환
    }

    /**
     * <p>싱글톤 테스트 용도</p>
     * @return memberRepository
     * @Return MemoryMemberRepository
     * @Description memberRepository를 반환 -> MemoryMemberRepository인스턴스 반환
     */
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

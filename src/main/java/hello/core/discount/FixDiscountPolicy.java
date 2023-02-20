package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

/**
 * 고정 할인 정책
 */
public class FixDiscountPolicy implements DiscountPolicy {
    
    private int discountFixAmount = 1000; // 1000원 할인
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {//enum타입은 ==을 쓰는게 맞다.
            return discountFixAmount; //vip면 1000원 고정 할인
        } else {
            return 0;
        }
    }
}

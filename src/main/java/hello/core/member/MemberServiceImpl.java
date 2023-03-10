package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 다형성에 의해 MemoryMemberRepository의 메소드를 호출한다.
    private final MemberRepository memberRepository;

    @Autowired //ac.getBean("memberRepository", MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 의존관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점 발생 (MemberRepository , MemoryMemberRepository)
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
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

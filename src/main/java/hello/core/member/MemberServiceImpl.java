package hello.core.member;

public class MemberServiceImpl implements MemberService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 다형성에 의해 MemoryMemberRepository의 메소드를 호출한다.
    private final MemberRepository memberRepository;

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
}

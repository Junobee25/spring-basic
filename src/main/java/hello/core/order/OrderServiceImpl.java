package hello.core.order;

import hello.core.discount.DisCountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private MemberRepository memberRepository;
    private DisCountPolicy disCountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DisCountPolicy disCountPolicy) {
        System.out.println("memberRepository" + memberRepository);
        System.out.println("disCountPolicy" + disCountPolicy);
        this.memberRepository = memberRepository;
        this.disCountPolicy = disCountPolicy;
    }

    @Autowired
    public void init(MemberRepository memberRepository, DisCountPolicy disCountPolicy) {
        this.memberRepository = memberRepository;
        this.disCountPolicy = disCountPolicy;
    }


    // DIP : 주문서비스 클라이언트인(OrderServiceImpl)은
    // 인터페이스만 의존하는거 같지만 구현클래스에도 의존 하고있다
    // DIP 위반
//    private final DisCountPolicy disCountPolicy = new RateDisCountPolicy();


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = disCountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

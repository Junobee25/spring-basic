package hello.core.order;

import hello.core.discount.DisCountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // 파이널 붙은 것을 가지고 생성자 만들어 줌
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DisCountPolicy disCountPolicy;
    
    //lombok이 만들어줌
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDisCountPolicy") DisCountPolicy disCountPolicy) {
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

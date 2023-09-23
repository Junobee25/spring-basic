package hello.core.order;

import hello.core.discount.DisCountPolicy;
import hello.core.discount.FixDisCountPolicy;
import hello.core.discount.RateDisCountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DisCountPolicy disCountPolicy = new FixDisCountPolicy();
    private final DisCountPolicy disCountPolicy = new RateDisCountPolicy();
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = disCountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}

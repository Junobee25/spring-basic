package hello.core;

import hello.core.discount.DisCountPolicy;
import hello.core.discount.FixDisCountPolicy;
import hello.core.discount.RateDisCountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceimpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService() {
        // 생성자 주입
        return new MemberServiceimpl(memberRepository());
    }

    private MemoryMemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        // 생성자 주입
        return new OrderServiceImpl(memberRepository(), disCountPolicy());
    }

    public DisCountPolicy disCountPolicy() {
        // 할인 정책 역할을 담당하는 구현 (객체 변경하면 끝)
        return new RateDisCountPolicy();
    }
}

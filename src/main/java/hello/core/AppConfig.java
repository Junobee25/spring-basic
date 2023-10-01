package hello.core;

import hello.core.discount.DisCountPolicy;
import hello.core.discount.FixDisCountPolicy;
import hello.core.discount.RateDisCountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceimpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        System.out.println("Call AppConfig.memberService");
        // 생성자 주입
        return new MemberServiceimpl(memberRepository());
    }
    @Bean
    public MemoryMemberRepository memberRepository(){
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        System.out.println("Call AppConfig.orderService");
        // 생성자 주입
//        return new OrderServiceImpl(memberRepository(), disCountPolicy());
        return null;
    }
    @Bean
    public DisCountPolicy disCountPolicy() {
        // 할인 정책 역할을 담당하는 구현 (객체 변경하면 끝)
        return new RateDisCountPolicy();
    }


}

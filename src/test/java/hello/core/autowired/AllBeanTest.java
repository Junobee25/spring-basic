package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DisCountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DisCountService.class);

        DisCountService disCountService = ac.getBean(DisCountService.class);
        Member userA = new Member(1L, "userA", Grade.VIP);
        int disCountPrice = disCountService.discount(userA,1000,"fixDisCountPolicy");

        Assertions.assertThat(disCountService).isInstanceOf(DisCountService.class);
        Assertions.assertThat(disCountPrice).isEqualTo(1000);

        int rateDisCountPrice = disCountService.discount(userA,20000,"rateDisCountPolicy");

        Assertions.assertThat(rateDisCountPrice).isEqualTo(2000);
    }
    static class DisCountService {
        private final Map<String, DisCountPolicy> policyMap;
        private final List<DisCountPolicy> policies;


        @Autowired
        public DisCountService(Map<String, DisCountPolicy> policyMap, List<DisCountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member userA, int price, String disCountCode) {
            DisCountPolicy disCountPolicy = policyMap.get(disCountCode);
            return disCountPolicy.discount(userA,price);
        };
    }
}

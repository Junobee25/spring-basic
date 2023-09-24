package hello.core.Beanfind;

import hello.core.AppConfig;
import hello.core.discount.DisCountPolicy;
import hello.core.discount.FixDisCountPolicy;
import hello.core.discount.RateDisCountPolicy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByParentTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DisCountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanByParentTypeBeanName() {
        DisCountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DisCountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDisCountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 지정") // 구체적인 타입으로 지정했으니 안좋은 방법
    void findBeanBySubType() {
        RateDisCountPolicy bean = ac.getBean(RateDisCountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(bean).isInstanceOf(RateDisCountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회")
    void findAllBeanByParentType() {
        Map<String, DisCountPolicy> beansOfType = ac.getBeansOfType(DisCountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);

        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));

        }
    }
    
    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    void findBeanByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));

        }
    }

    @Configuration
    static class TestConfig {

        @Bean
        public DisCountPolicy rateDiscountPolicy() {
            return new RateDisCountPolicy();
        }

        @Bean
        public DisCountPolicy fixDiscountPolicy() {
            return new FixDisCountPolicy();
        }
    }
}

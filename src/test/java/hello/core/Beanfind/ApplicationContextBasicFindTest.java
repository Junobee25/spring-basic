package hello.core.Beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceimpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByname(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceimpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceimpl.class);
    }
    
    
    // 항상 역할에 의존하도록 해야되기 때문에 구현체인 impl로 조회하는건 좋지 않음(유연성이 떨어짐)
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByname2(){
        MemberServiceimpl memberService = ac.getBean("memberService", MemberServiceimpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceimpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanBynameX(){
//        ac.getBean("xxxxx", MemberService.class);
//        MemberService xxxxx = ac.getBean("xxxxx", MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
    }
}

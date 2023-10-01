package hello.core;


import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 탐색 시작 위치. core만 컴포넌트 스캔의 대상이 됨 다른 경로 안 찾아도 되니 효율적임
        // default는 ComponentScan 넣어준 class의 패키지 위치 => hello.core
        // 보통 autoconfig, appconfig같은 설정 정보 클래스의 위치를 프로젝트 최상단에 두면 basePackages 추가할 필요 없음.
        basePackages = "hello.core",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)

public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}

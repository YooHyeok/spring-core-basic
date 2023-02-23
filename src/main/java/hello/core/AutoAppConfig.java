package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( //default는 @ComponentScan이 붙은 AutoAppConfig 클래스의 패키지가 시작 위치가 된다.
//        basePackages = "hello.core.member", //member 패키지만 ComponentScan의 대상이 된다. - 모든 라이브러리까지 다 뒤지기때문에 지정해주는게 좋다.
        basePackageClasses = AutoAppConfig.class, // AutoAppConfig 클래스의 패키지 hello.core 를 탐색시작위치로 지정한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)// 등록하지 않을것 지정
)
public class AutoAppConfig {
    /**
     * 수동으로 등록 빈을 등록한다.
     * 수동 등록 빈 네임이 중복될경우 수동 빈 등록이 우선권을 가진다.
     * 수동 빈이 자동 빈을 오버라이딩 해버린다.
     * 콘솔창에 Overriding bean definition for bean 라고 출력된다.
     * 스프링 부트에서는 그냥 오류를 출력시켜 버리도록 Overriding이 False로 패치됨. 아래 출력 (properties에 세팅해줘야 overiding이 가능해짐)
     * Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true
     * @return
     */
    @Bean(name="memoryMemberRepository") //수동 등록 빈 네임이 중복될경우 수동등록 빈
    MemberRepository memberRepository () {
        return new MemoryMemberRepository();
    }
}

package hello.core;

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

}

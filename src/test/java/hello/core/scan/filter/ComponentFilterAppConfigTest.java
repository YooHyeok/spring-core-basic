package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {

    /**
     * FilterType.ANNOTATION은 Default이므로 생략이 가능하다.
     * ANNOTATION : 기본값, 애노테이션을 인식하여 동작
     * ASSIGNABLE_TYPE : 지정한 타입과 자식 타입을 인식해서 동작 ex) BeanA를 빼고싶다면 FilterType.ASSIGNABLE_TYPE, BeanA.class로 지정한다.
     * ASPECTJ : AspectJ 패턴 사용
     * REGEX : 정규표현식 사용
     * CUSTOM : TypeFilter 인터페이스를 구현해서 처리한다.
     */
    @Configuration
    @ComponentScan(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class)
                , excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class))
    static class ComponentFilterAppConfig {}
    @Test
    void filterScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();
//        ac.getBean("beanB",BeanB.class); // BeanB 클래스에 선언한 @MyExcludeComponent 애노테이션을 excludeFilters에 등록했기때문에 빈 등록에 제외된다.
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanB.class));
    }

}


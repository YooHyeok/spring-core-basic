package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class); // getBeanDefinition을 사용하려면 반환을 클래스로해야한다.

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " Object = " + bean);
        }
    }

    /**
     * 애플리케이션 빈만 출력하기.
     */
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName); // beanDefinition : 빈에대한 메타데이터 정보
            
            // Role ROLE_APPLICATON : 직접 등록한 애플리케이션 빈 
            // Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) { // 스프링 내부가 아니라 애플리케이션을 개발하기위한 역할의 빈
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " Object = " + bean);
            }
        }
    }
    /**
     * entrySet 사용
     * @Component 빈만 출력하기.
     */
    @Test
    @DisplayName("컴포넌트 빈 출력하기")
    void findComponentBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        Map<String, Object> beansWithAnnotation = ac.getBeansWithAnnotation(Component.class);// beanDefinition : 빈에대한 메타데이터 정보
        for (Map.Entry<String, Object> entryBean : beansWithAnnotation.entrySet()) { // map의  key와 value를 가지는 Set 객체를 리턴
            String beanName = entryBean.getKey();
            Object beanObject = entryBean.getValue();
            System.out.println("name : " + beanName);
            System.out.println("Object : " + beanObject.getClass().getCanonicalName());
        }
    }

    /**
     * forEach 사용
     * @Component 빈만 출력하기.
     */
    @Test
    @DisplayName("컴포넌트 빈 출력하기2")
    void findComponentBean2() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        Map<String, Object> beansWithAnnotation = ac.getBeansWithAnnotation(Component.class);// beanDefinition : 빈에대한 메타데이터 정보
        beansWithAnnotation.forEach((key, value) -> {
            System.out.println("name = " + key);
            System.out.println("Object = " + value);
        });
    }
}

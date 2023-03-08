package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class); // url은 null이다.
        ac.close();
    }
    @Configuration
    static class LifeCycleConfig {
        @Bean(initMethod = "init", destroyMethod = "close")
        // 등록할 Bean 의 대상 클래스인 NetworkClient.java 파일의 메소드명을 등록한다.
        //destroyMethod 속성은 default가 (inffered) 추론 이기 때문에 속성을 생략할 경우 해당 빈 클래스에 close나 shutdown과 같은 이름의 메소드를 호출한다.
        // 추론 기능을 사용하지 않으려면 destroyMethod = "" 과 같이 공백으로 지정해주면 된다.
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("https://github.com/YooHyeok");
            return networkClient;
        }
    }
}

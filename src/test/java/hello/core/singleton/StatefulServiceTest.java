package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        /* 상태유지(stateful)시 문제 발생 */
//        statefulService1.order("userA", 10000); // ThreadA : A사용자 10000원 주문
//        statefulService2.order("userB", 20000); // ThreadB : B사용자 20000원 주문
//        int price = statefulService1.getPrice(); // ThreadA : 사용자 A 주문 금액 조회
//        System.out.println("price = " + price);
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

        /* 무상태 */
        int userAPrice = statefulService1.order("userA", 10000);//ThreadA : A사용자 10000원 주문
        int userBPrice = statefulService2.order("userB", 20000);//ThreadB : B사용자 20000원 주문
        System.out.println("userBPrice = " + userBPrice);
        Assertions.assertThat(userAPrice).isEqualTo(10000);
        Assertions.assertThat(userBPrice).isEqualTo(20000);

    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService () {
            return new StatefulService();
        }
    }

}
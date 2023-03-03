package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        // 수정자 주입
        //Member 객체를 수정자를 통해 의존성 주입을 하려 하지만, 자동주입할 대상이 아니므로(@Component가 아닌 일반 VO객체) 수정자 메서드 자체가 호출되지 않는다.
        @Autowired(required = false) //자동 주입할 대상이 없으면 수정자 자체가 호출 되지 않는다.
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) { //호출은 되지만 null로 처리
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired(required = false)
        public void setNoBean3(Optional<Member> noBean3) { //자바8 Optional문법 - Bean이 존재하지않으면 Optional.empty로 처리
            System.out.println("noBean3 = " + noBean3);
        }
    }
}

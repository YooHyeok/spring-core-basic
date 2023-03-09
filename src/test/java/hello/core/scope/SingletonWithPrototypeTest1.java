package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    /**
     * 스프링은 일반적으로 싱글톤 빈을 사용하므로, 싱글톤 빈이 프로토타입 빈을 사용하게 된다.
     * 싱글톤 빈은 생성 시점에서만 의존관계 주입을 받기 때문에, 프로토타입 빈이 새로 생성되기는 하지만, 싱글톤 빈과 함께 계속 유지된다.
     */
    @Test
    void singletonClientUserPrototype1() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int logic1 = clientBean1.singletonLogic();
        assertThat(logic1).isEqualTo(1);
        ClientBean clientBean2 = ac.getBean(ClientBean.class); // Singleton에 의해 처음 생성 시점에 주입되었던 빈을 그대로 사용
        int logic2 = clientBean2.singletonLogic();
        assertThat(logic2).isEqualTo(2);
    }

    /**
     * ApplicationContext 의존성 주입 테스트
     * 단점 : 스프링 컨테이너에 종속적이게 된다.
     */
    @Test
    void singletonClientUserPrototype2() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int logic1 = clientBean1.prototypeLogic();
        assertThat(logic1).isEqualTo(1);
        ClientBean clientBean2 = ac.getBean(ClientBean.class); // Singleton에 의해 처음 생성 시점에 주입되었던 빈을 그대로 사용
        int logic2 = clientBean2.prototypeLogic();
        assertThat(logic1).isEqualTo(1);
    }

    /**
     * javax.inject - provider
     * JSR-330 자바표준 - 스프링이 아닌 다른 컨테이너에서도 사용이 가능하다.
     */
    @Test
    void singletonClientUserPrototype3() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int logic1 = clientBean1.providerLogic();
        assertThat(logic1).isEqualTo(1);
        ClientBean clientBean2 = ac.getBean(ClientBean.class); // Singleton에 의해 처음 생성 시점에 주입되었던 빈을 그대로 사용
        int logic2 = clientBean2.providerLogic();
        assertThat(logic1).isEqualTo(1);
    }

    @RequiredArgsConstructor // final field constructor Dependency Injection
    @Scope("singleton")
    static class ClientBean {
        private final PrototypeBean prototypeBean; //ClientBean의 생성 시점에 주입된다.
        private final ApplicationContext ac; // 스프링 컨테이너 주입
        private final ObjectFactory<PrototypeBean> prototypeBeanFactory; // DependencyLookup 스프링컨테이너에 요청해서 빈을 반환해준다.
        private final ObjectProvider<PrototypeBean> prototypeBeanProvider; // DependencyLookup ObjectFactory에 편의기능을 추가한 구현체
        private final Provider<PrototypeBean> provider; // DependencyLookup javax.inject - provider JSR-330 자바표준
        public int singletonLogic() {
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
        public int prototypeLogic() {
            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
        public int objectproviderLogic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
        public int providerLogic() {
            PrototypeBean prototypeBean = provider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }
    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;
        public void addCount() {
            count++;
        }
        public int getCount() {
            return count;
        }
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init "+this);
        }
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }

    }
}

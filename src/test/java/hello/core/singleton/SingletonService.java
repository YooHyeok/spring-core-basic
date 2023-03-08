package hello.core.singleton;

/**
 * 싱글톤 패턴
 * 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴
 * 객체 인스턴스를 2개 이상 생성하지 못하도록 막아야 한다.
 * private 새성자를 사용해 외부에서 임의로 new 키워드를 사용하지 못하도록 막아야 한다.
 */
public class SingletonService {

    private static final SingletonService instance = new SingletonService(); // static으로 자기자신 인스턴스 생성

    public static SingletonService getInstance() { //getInstance메소드를 호출하여
        return instance;
    }

    private SingletonService() { //private으로 생성자를 만들면 다른 클래스에서 인스턴스 객체를 생성할 수 없다.

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}



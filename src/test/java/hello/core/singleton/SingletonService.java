package hello.core.singleton;

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



package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // TARGET_CLASS : 적용대상이 클래스
public class MyLogger {//Http 요청당 Bean이 하나씩 생성되고, Http 요청이 끝나는 시점에 소멸된다.
    private String uuid;
    private String requestURL;

    /**
     * 빈이 생성되는 시점에 값을 넣을수 없으므로 setter 생성
     * @param requestURL
     */
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }
    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    /**
     * 빈이 생성되는 시점에 초기화 메서드 호출
     * uuid를 생성하여 저장 - 다른 HTTP 요청과 구분할 구분값
     */
    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this);

    }

    @PreDestroy
    public void close() { //request가 끝나면 호출된다.
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}

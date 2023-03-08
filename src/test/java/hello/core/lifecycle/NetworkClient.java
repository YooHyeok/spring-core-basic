package hello.core.lifecycle;

//public class NetworkClient implements InitializingBean, DisposableBean {
public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }
    public void call(String message) {
        System.out.println("call : " + url + " message = " + message);
    }
    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }

    public void init() { //의존관계 주입 종료시 호출
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    public void close() { // 빈 종료시 호출
        System.out.println("NetworkClient.close");
        disconnect();
    }
//    @Override // InitializingBean 초기화 인터페이스
//    public void afterPropertiesSet() throws Exception { //의존관계 주입 종료시 호출
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메시지");
//    }
//
//    @Override // DisposableBean 소멸 인터페이스
//    public void destroy() throws Exception { // 빈 종료시 호출
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }
}

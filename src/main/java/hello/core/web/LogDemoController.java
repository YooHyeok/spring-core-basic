package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final Provider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString(); //url추출
        MyLogger myLogger = myLoggerProvider.get();
        myLogger.setRequestURL(requestURL); //url주입
        myLogger.log("controller test");
        Thread.sleep(1000); //요청이 섞여도 구분이 가능해진다.
        logDemoService.logic("testId");
        return "ok";
    } 
}

package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.inject.Provider;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    private final Provider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;


    public void logic(String id) {
//        MyLogger myLogger = myLoggerProvider.get();
        myLogger.log("service ID = " + id);
    }
}

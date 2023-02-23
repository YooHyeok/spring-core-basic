package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //Type 은 Class레벨에 붙는다.
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {

}

package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(   type = FilterType.ANNOTATION
                                                , classes = Configuration.class)
) // 등록하지 않을것 지정
public class AutoAppConfig {

}

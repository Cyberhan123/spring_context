package cn.hselfweb.cnjpbbs.configuration;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ApplicationConfiguration {
    public @Bean
    CustomizableTraceInterceptor interceptor() {
        CustomizableTraceInterceptor interceptor = new CustomizableTraceInterceptor();
        interceptor.setEnterMessage("进入 $[methodName]($[arguments]).");
        interceptor.setExitMessage("离开 $[methodName](..) 带有返回值  $[returnValue], 花费时间 $[invocationTime]ms.");
        return interceptor;
    }

    public @Bean
    Advisor traceAdvisor() {

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(public * org.springframework.data.repository.Repository+.*(..))");
        return new DefaultPointcutAdvisor(pointcut, interceptor());
    }
}

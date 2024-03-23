package com.example.demo;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class ProxyFactoryBeanTest {
    @Bean
    public MethodInterceptor zhouyuAroundAdvise(){
        return new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.out.println("before...");
                Object result = invocation.proceed();
                System.out.println("after...");
                return result;
            }
        };
    }
    @Bean
    public ProxyFactoryBean userService(){
        UserService userService = new UserService();
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(userService);
        proxyFactoryBean.setInterceptorNames("zhouyuAroundAdvise");
        return proxyFactoryBean;
    }

}

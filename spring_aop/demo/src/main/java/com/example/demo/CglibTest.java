package com.example.demo;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class CglibTest {
    public static void test(){
        UserService target = new UserService();
        // 通过cglib技术
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        // 定义额外逻辑，也就是代理逻辑
        enhancer.setCallbacks(new Callback[]{new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy
                    methodProxy) throws Throwable {
                System.out.println("before...");
                Object result = methodProxy.invoke(target, objects);
                System.out.println("after...");
                return result;
            }
        }});
        // 动态代理所创建出来的UserService对象
        UserService userService = (UserService) enhancer.create();
        // 执行这个userService的test方法时，就会额外会执行一些其他逻辑
        userService.test();

    }


}

package com.greatwqs.app.proxy;

import java.lang.reflect.Method;

import com.greatwqs.app.proxy.real.Person;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CgLibProxy implements MethodInterceptor {
	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("Before Proxy");
		Object result = methodProxy.invokeSuper(proxy, args);
		System.out.println("After Proxy");
		return result;
	}

	public static Person getProxyInstance() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Person.class);

		enhancer.setCallback(new CgLibProxy());
		return (Person)enhancer.create();
	}

	public static void main(String[] args) {
		getProxyInstance().doSomething();
	}
}
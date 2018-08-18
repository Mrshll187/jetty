package xxx.xxx.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import xxx.xxx.interceptor.MethodInterceptor;
import xxx.xxx.interceptor.InterceptorImpl;

@Retention(RUNTIME)
@Target(METHOD)
public @interface Intercept {
	
	String attribute();
	Class<? extends MethodInterceptor> interceptor() default InterceptorImpl.class;
}

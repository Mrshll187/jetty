package xxx.xxx.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Interceptor<T> implements InvocationHandler {

	private Object object = null;
	
    public Interceptor(Class<T> clazz) throws InstantiationException, IllegalAccessException {
    	this.object = clazz.newInstance();
    }

    private void before() {
    	System.out.println("Before Method");
    }
    
    private void after() {
    	System.out.println("After Method");
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        
    	before();
        Object result = method.invoke(object, args);
        after();
        
        return result;
    }

    @SuppressWarnings("unchecked")
	public static <T> T getProxy(Class<T> clazz) throws InstantiationException, IllegalAccessException {
    	return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new Interceptor<T>(clazz));
    }
}
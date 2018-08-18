package xxx.xxx.interceptor;

import java.lang.reflect.Method;

public interface MethodInterceptor {

	public void before(Object self, Method m, Method proceed, Object[] args);
	
	public void after(Object self, Method m, Method proceed, Object[] args);
}

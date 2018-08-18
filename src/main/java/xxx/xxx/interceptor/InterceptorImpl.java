package xxx.xxx.interceptor;

import java.lang.reflect.Method;

public class InterceptorImpl implements MethodInterceptor {

	@Override
	public void before(Object self, Method m, Method proceed, Object[] args) {
		return;
	}

	@Override
	public void after(Object self, Method m, Method proceed, Object[] args) {
		return;
	}

}

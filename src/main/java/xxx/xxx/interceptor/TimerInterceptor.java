package xxx.xxx.interceptor;

import java.lang.reflect.Method;
import java.util.logging.Logger;

public class TimerInterceptor implements MethodInterceptor {

	private Logger logger = Logger.getLogger(TimerInterceptor.class.getName());
	private long start;
	
	@Override
	public void before(Object self, Method m, Method proceed, Object[] args) {
		
		start = System.currentTimeMillis();
		logger.info("Method started");
	}

	@Override
	public void after(Object self, Method m, Method proceed, Object[] args) {
		
		logger.info("Method finished & elapsed " + (start - System.currentTimeMillis()));
	}

}

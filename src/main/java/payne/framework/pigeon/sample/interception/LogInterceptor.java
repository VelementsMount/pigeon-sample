package payne.framework.pigeon.sample.interception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import payne.framework.pigeon.core.Interceptor;
import payne.framework.pigeon.core.Invocation;

/**
 * 一个很简单的日志拦截器
 * 
 * @author yangchangpei
 *
 */
public class LogInterceptor implements Interceptor {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 这里可以获取到invocation对象,invocation包含了所有请求的信息, 框架实现了round方式的拦截. 所以必须调用
	 * {@link Invocation#invoke()}方法,框架才能往下走,invocation里还包含很多其他有用的信息,方便用户进行拦截操作.
	 */
	public Object intercept(Invocation invocation) throws Exception {
		try {
			logger.info("{} starting", invocation.getPath());
			return invocation.invoke();
		} catch (Exception e) {
			logger.error("{} exception thrown", invocation.getPath(), e);
			throw e;
		} finally {
			logger.info("{} completed", invocation.getPath());
		}
	}
}

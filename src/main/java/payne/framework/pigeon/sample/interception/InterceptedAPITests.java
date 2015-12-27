package payne.framework.pigeon.sample.interception;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import payne.framework.pigeon.client.Client;
import payne.framework.pigeon.core.Interceptor;
import payne.framework.pigeon.core.Invocation;
import payne.framework.pigeon.server.BlockingInvocationContext;
import payne.framework.pigeon.server.InvocationContext;

public class InterceptedAPITests {
	private InvocationContext context;
	private InterceptedAPI api;

	@Before
	public void initialize() throws Exception {
		context = new BlockingInvocationContext();
		context.register(new InterceptedAPIImpl());
		context.bind(9090);
		context.startup();

		Client client = new Client("localhost", 9090);
		// 不仅是服务端可以添加拦截器,客户端也是可以添加拦截器的.只需要在后面的可变参数写上一个或多个拦截器实例即可
		api = client.create("HTTP/1.1", "application/json", "/InterceptedAPIImpl", InterceptedAPI.class, new ClientLogInterceptor());
	}

	@After
	public void destroy() throws Exception {
		context.shutdown();
	}

	@Test
	public void testFoo() throws Exception {
		api.foo();
	}

	@Test
	public void testBar() throws Exception {
		api.bar();
	}

	/**
	 * 客户端日志拦截器
	 * 
	 * @author yangchangpei
	 *
	 */
	private static class ClientLogInterceptor implements Interceptor {
		private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

}

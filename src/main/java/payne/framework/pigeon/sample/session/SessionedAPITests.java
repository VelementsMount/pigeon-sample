package payne.framework.pigeon.sample.session;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import payne.framework.pigeon.client.Client;
import payne.framework.pigeon.client.cookie.CookieInterceptor;
import payne.framework.pigeon.client.cookie.InternalCookieManager;
import payne.framework.pigeon.server.BlockingInvocationContext;
import payne.framework.pigeon.server.InvocationContext;

public class SessionedAPITests {

	private InvocationContext context;
	private SessionedAPI api;

	@Before
	public void initialize() throws Exception {
		context = new BlockingInvocationContext();
		context.register(new SessionedAPIImpl());
		context.bind(9090);
		context.startup();

		Client client = new Client("localhost", 9090);
		// 记得一定要加上CookieInterceptor
		api = client.create("HTTP/1.1", "application/json", "/SessionedAPIImpl", SessionedAPI.class, new CookieInterceptor(new InternalCookieManager()));
	}

	@After
	public void destroy() throws Exception {
		context.shutdown();
	}

	@Test
	public void testSession() throws Exception {
		boolean logined = api.login("zhangsan", "123456");
		Assert.assertEquals(true, logined);
		boolean logouted = api.logout();
		Assert.assertEquals(true, logouted);
	}

}

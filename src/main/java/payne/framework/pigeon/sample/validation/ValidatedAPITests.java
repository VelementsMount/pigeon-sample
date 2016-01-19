package payne.framework.pigeon.sample.validation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import payne.framework.pigeon.client.Client;
import payne.framework.pigeon.generation.objc.ObjectiveCGeneratorService;
import payne.framework.pigeon.server.InvocationContext;
import payne.framework.pigeon.server.bio.BlockingInvocationContext;

public class ValidatedAPITests {

	private InvocationContext context;
	private ValidatedAPI api;

	public static void main(String[] args) throws Exception {
		InvocationContext context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new ValidatedAPIImpl());
		context.register(new ObjectiveCGeneratorService(false));
		context.startup();
	}

	@Before
	public void initialize() throws Exception {
		context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new ValidatedAPIImpl());
		context.startup();
		Client client = new Client("localhost", 9090, "pigeon.properties", "pigeon-encryption.properties");
		api = client.create("HTTP/1.0", "application/x-java-serialized-object", "/ValidatedAPIImpl", ValidatedAPI.class);
	}

	@After
	public void destroy() throws Exception {
		context.shutdown();
	}

	@Test
	public void testRegister() throws Exception {
		// 这样是校验通过的
		api.register(new User("zhangsan", "123456"));
		// 下面这几种方式都是校验失败的
		// api.register(new User("zhangsan", null));
		// api.register(new User(null, "123456"));
		// api.register(null);
	}

	@Test
	public void testLogin() throws Exception {
		api.login("zhangsan", "123456");
		// 下面这几种方式都是校验失败的
		// api.login(null, "123456");
		// api.login("zhangsan", null);
	}

}

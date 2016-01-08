package payne.framework.pigeon.sample.helloworld;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import payne.framework.pigeon.client.Client;
import payne.framework.pigeon.core.factory.bean.SingletonBeanFactory;
import payne.framework.pigeon.core.formatting.InvocationFormatter;
import payne.framework.pigeon.server.InvocationContext;
import payne.framework.pigeon.server.bio.BlockingInvocationContext;

public class SimpleAPITests {
	private InvocationContext context;
	private SimpleAPI api;

	public static void main(String[] args) throws Exception {
		new SingletonBeanFactory().get("application/json", InvocationFormatter.class);
	}

	@Before
	public void initialize() throws Exception {
		context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new SimpleAPIImpl());
		context.startup();
		Client client = new Client("localhost", 9090);
		api = client.create("HTTP", "application/json", "/SimpleAPIImpl", SimpleAPI.class);
	}

	@After
	public void destroy() throws Exception {
		context.shutdown();
	}

	@Test
	public void testHello() throws Exception {
		String hi = api.hello("Hello Server I am Client!");
		System.out.println("Server Say : " + hi);
	}

}

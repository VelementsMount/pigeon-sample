package payne.framework.pigeon.sample.helloworld;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import payne.framework.pigeon.client.Client;
import payne.framework.pigeon.generation.objc.ObjectiveCGeneratorService;
import payne.framework.pigeon.server.BlockingInvocationContext;
import payne.framework.pigeon.server.InvocationContext;

public class SimpleAPITests {
	private InvocationContext context;
	private SimpleAPI api;

	public static void main(String[] args) throws Exception {
		InvocationContext context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new SimpleAPIImpl());
		context.register(new ObjectiveCGeneratorService(false));
		context.startup();
	}

	@Before
	public void initialize() throws Exception {
		context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new SimpleAPIImpl());
		context.startup();
		Client client = new Client("localhost", 9090);
		api = client.create("HTTP", "application/x-java-serialized-object", "/SimpleAPIImpl", SimpleAPI.class);
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

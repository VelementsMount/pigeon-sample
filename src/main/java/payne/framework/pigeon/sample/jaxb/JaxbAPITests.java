package payne.framework.pigeon.sample.jaxb;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import payne.framework.pigeon.client.Client;
import payne.framework.pigeon.generation.objc.ObjectiveCGeneratorService;
import payne.framework.pigeon.server.BlockingInvocationContext;
import payne.framework.pigeon.server.InvocationContext;

public class JaxbAPITests {

	private InvocationContext context;
	private JaxbAPI api;

	public static void main(String[] args) throws Exception {
		InvocationContext context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new JaxbAPIImpl());
		context.register(new ObjectiveCGeneratorService(false));
		context.startup();
	}

	@Before
	public void initialize() throws Exception {
		context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new JaxbAPIImpl());
		context.startup();
		Client client = new Client("localhost", 9090, "pigeon.properties");
		api = client.create("HTTP/1.0", "application/xml", "/JaxbAPIImpl", JaxbAPI.class);
	}

	@After
	public void destroy() throws Exception {
		context.shutdown();
	}

	@Test
	public void testGetOrderById() throws Exception {
		Order order = api.getOrderById(1, "sdf");
		System.out.println(order);
	}

}

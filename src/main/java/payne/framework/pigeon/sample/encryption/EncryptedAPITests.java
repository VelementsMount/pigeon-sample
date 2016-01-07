package payne.framework.pigeon.sample.encryption;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import payne.framework.pigeon.client.Client;
import payne.framework.pigeon.sample.jaxb.Customer;
import payne.framework.pigeon.server.InvocationContext;
import payne.framework.pigeon.server.bio.BlockingInvocationContext;

public class EncryptedAPITests {
	private InvocationContext context;
	private EncryptedAPI api;

	/**
	 * 在启动服务端的时候就要把刚才我们写的rsa-private.properties文件也配置进去<br/>
	 * 我们看到beanConfigurationPaths是一个数组,虽然是加上rsa的配置文件但是也要默认的功能能正常工作,<br/>
	 * 所以还要加上pigeon. properties
	 * 
	 * @throws Exception
	 */
	@Before
	public void initialize() throws Exception {
		context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new EncryptedAPIImpl());
		context.setBeanConfigurationPaths(new String[] { "pigeon.properties", "rsa-private.properties" });
		context.startup();
		Client client = new Client("localhost", 9090, "pigeon.properties", "rsa-public.properties");
		api = client.create("HTTP/1.1", "application/x-java-serialized-object", "/EncryptedAPIImpl", EncryptedAPI.class);
	}

	@After
	public void destroy() throws Exception {
		context.shutdown();
	}

	@Test
	public void testLogin() throws Exception {
		Customer customer = api.login("payne", "123456");
		Assert.assertNotNull(customer);
		System.out.println(customer);
	}

}

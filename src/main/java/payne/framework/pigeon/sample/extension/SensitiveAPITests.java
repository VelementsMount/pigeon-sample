package payne.framework.pigeon.sample.extension;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import payne.framework.pigeon.client.Client;
import payne.framework.pigeon.server.BlockingInvocationContext;
import payne.framework.pigeon.server.InvocationContext;

/**
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Company: 广州市俏狐信息科技有限公司
 * </p>
 * 
 * @author yangchangpei 646742615@qq.com
 *
 * @date 2015年7月27日 上午10:57:06
 *
 * @version 1.0.0
 */
public class SensitiveAPITests {

	private InvocationContext context;
	private SensitiveAPI api;

	@Before
	public void initialize() throws Exception {
		context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new SensitiveAPIImpl());
		context.startup();
		Client client = new Client("localhost", 9090);
		api = client.create("HTTP/1.1", "application/json", "/SensitiveAPIImpl", SensitiveAPI.class);
	}

	@After
	public void destroy() throws Exception {
		context.shutdown();
	}

	@Test
	public void testSensitive() throws Exception {
		api.hello("敏感词语1");
	}

	@Test
	public void testUnsensitive() throws Exception {
		api.hello("和谐词语1");
	}

}

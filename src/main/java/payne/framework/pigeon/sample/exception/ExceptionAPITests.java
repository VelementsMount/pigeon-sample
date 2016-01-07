package payne.framework.pigeon.sample.exception;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import payne.framework.pigeon.client.Client;
import payne.framework.pigeon.server.InvocationContext;
import payne.framework.pigeon.server.bio.BlockingInvocationContext;

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
 * @date 2015年7月25日 下午3:37:06
 *
 * @version 1.0.0
 */
public class ExceptionAPITests {

	private InvocationContext context;
	private ExceptionAPI api;

	public static void main(String[] args) throws Exception {
		InvocationContext context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new ExceptionAPIImpl());
		context.startup();
	}

	@Before
	public void initialize() throws Exception {
		context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new ExceptionAPIImpl());
		context.startup();
		Client client = new Client("localhost", 9090);
		api = client.create("HTTP/1.1", "application/x-java-serialized-object", "/ExceptionAPIImpl", ExceptionAPI.class);
	}

	@After
	public void destroy() throws Exception {
		context.shutdown();
	}

	@Test
	public void testDownload() throws Exception {
		try {
			api.download("always exception");
		} catch (Exception e) {
			// 这里输出的异常实际上会把服务端的所有异常踪迹都输出,方便使用者调试.但也会带来隐患,因为这样服务端的代码逻辑可能会被知道.
			e.printStackTrace();
		}
	}

}

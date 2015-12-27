package payne.framework.pigeon.sample.advance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import payne.framework.pigeon.client.Client;
import payne.framework.pigeon.server.AsynchronousNonBlockingInvocationContext;

/**
 * <p>
 * Description:异步非阻塞IO实际上综合两者的优点实际上看来这个可以适合所有的需求但是要求JDK1.7以上所以这也是我们应该考虑的范围以内.
 * </p>
 * 
 * <p>
 * Company: 广州市俏狐信息科技有限公司
 * </p>
 * 
 * @author yangchangpei 646742615@qq.com
 *
 * @date 2015年8月28日 上午10:06:56
 *
 * @version 1.0.0
 */
public class AsynchronousNonBlockingInvocationContextTests {

	private AsynchronousNonBlockingInvocationContext context;
	private SameAPI sameAPI;

	@Before
	public void initialize() throws Exception {
		context = new AsynchronousNonBlockingInvocationContext();
		context.register(new SameAPIImpl());
		context.bind(9090);
		context.startup();
		
		Thread.sleep(100);
		
		Client client = new Client("localhost", 9090);
		sameAPI = client.create("HTTP/1.1", "application/x-java-serialized-object", "/SameAPIImpl", SameAPI.class);
	}

	@After
	public void destroy() throws Exception {
		context.shutdown();
	}

	@Test
	public void testDoTheSameThing() throws Exception {
		String result = sameAPI.doTheSameThing("anything");
		System.out.println(result);
	}

}

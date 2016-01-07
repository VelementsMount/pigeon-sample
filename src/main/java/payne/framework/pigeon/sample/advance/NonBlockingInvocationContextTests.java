package payne.framework.pigeon.sample.advance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import payne.framework.pigeon.client.Client;
import payne.framework.pigeon.server.nio.NonBlockingInvocationContext;

/**
 * <p>
 * Description:同步非阻塞IO比较适合链接数量比较大而单个链接传送的数据量比较小的需求
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
public class NonBlockingInvocationContextTests {

	private NonBlockingInvocationContext context;
	private SameAPI sameAPI;

	@Before
	public void initialize() throws Exception {
		context = new NonBlockingInvocationContext();
		context.register(new SameAPIImpl());
		context.bind(9090);
		context.startup();
		
		Thread.sleep(10);
		
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

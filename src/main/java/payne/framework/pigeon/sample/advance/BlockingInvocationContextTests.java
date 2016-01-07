package payne.framework.pigeon.sample.advance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import payne.framework.pigeon.client.Client;
import payne.framework.pigeon.server.bio.BlockingInvocationContext;

/**
 * <p>
 * Description:阻塞IO比较适合链接数量比较少而单个连接传送数据量比较大的需求
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
public class BlockingInvocationContextTests {

	private BlockingInvocationContext context;
	private SameAPI sameAPI;

	@Before
	public void initialize() throws Exception {
		context = new BlockingInvocationContext();
		context.register(new SameAPIImpl());
		context.bind(9090);
		context.startup();

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

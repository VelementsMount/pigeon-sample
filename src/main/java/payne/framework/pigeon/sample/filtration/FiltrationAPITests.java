package payne.framework.pigeon.sample.filtration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import payne.framework.pigeon.client.Client;
import payne.framework.pigeon.sample.helloworld.SimpleAPI;
import payne.framework.pigeon.sample.helloworld.SimpleAPIImpl;
import payne.framework.pigeon.server.InvocationContext;
import payne.framework.pigeon.server.bio.BlockingInvocationContext;

/**
 * <p>
 * Description:这里直接采用{@link SimpleAPI}作为开放接口做例子,在{@link InvocationContext}添加一个
 * {@link LogFilter}过滤器实现最简单的日志功能
 * </p>
 * 
 * <p>
 * Company: 广州市俏狐信息科技有限公司
 * </p>
 * 
 * @author yangchangpei 646742615@qq.com
 *
 * @date 2015年7月25日 下午4:00:15
 *
 * @version 1.0.0
 */
public class FiltrationAPITests {

	private InvocationContext context;
	private SimpleAPI api;

	public static void main(String[] args) throws Exception {
		InvocationContext context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new SimpleAPIImpl());
		context.add(new LogFilter());// 添加过滤器
		context.startup();
	}

	@Before
	public void initialize() throws Exception {
		context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new SimpleAPIImpl());
		context.add(new LogFilter());// 添加过滤器
		context.startup();

		Client client = new Client("localhost", 9090);
		api = client.create("HTTP/1.0", "application/x-java-serialized-object", "/SimpleAPIImpl", SimpleAPI.class);
	}

	@After
	public void destroy() throws Exception {
		context.shutdown();
	}

	/**
	 * 执行单元测试方法 观察控制台输入的日志即可
	 * 
	 * @throws Exception
	 */
	@Test
	public void testHello() throws Exception {
		String hi = api.hello("Hello Server I am Client!");
		System.out.println("Server Say : " + hi);
	}

}

package payne.framework.pigeon.sample.asynchronous;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import payne.framework.pigeon.client.AsyncClient;
import payne.framework.pigeon.core.CallbackAdapter;
import payne.framework.pigeon.generation.Interface;
import payne.framework.pigeon.generation.async.AsynchronousGenerator;
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
 * @date 2015年8月27日 下午8:54:00
 *
 * @version 1.0.0
 */
public class AsyncAPITests {
	private InvocationContext context;
	private AsyncAsyncAPI api;

	/**
	 * 生成异步接口并且复制到我们的包里面,生成文件的文件夹路径根据自己实际情况修改
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGenerateAsyncAPI() throws Exception {
		File directory = new File("/Users/yangchangpei/Project/async-gen");
		if (!directory.exists()) {
			directory.mkdirs();
		}
		AsynchronousGenerator generator = new AsynchronousGenerator(directory);
		generator.generate(new Interface("/AsyncAPIImpl", AsyncAPI.class));
	}

	@Before
	public void initialize() throws Exception {
		context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new AsyncAPIImpl());
		context.startup();

		Thread.sleep(10);
		// 注意这里采用的是AsyncClient而且我们创建的是AsyncAsyncAPI这个带有回调函数的异步接口
		// 如果想实现Android那样的主线程代码调用实际用子线程请求服务端最后主线程回调函数就要采用AndroidClient但是没有那个环境所以用这个代替
		AsyncClient client = new AsyncClient("localhost", 9090);
		api = client.create("HTTP/1.0", "application/x-java-serialized-object", "/AsyncAPIImpl", AsyncAsyncAPI.class);
	}

	@After
	public void destroy() throws Exception {
		context.shutdown();
	}

	@Test
	public void testAsyncAPI() throws Exception {
		final Object lock = new Object();

		System.out.println("代码调用线程ID:" + Thread.currentThread().getId());

		// 这里前两个参数是真正的请求参数,而CallbackAdapter的回调的适配器,用这个最为匿名内部类的父类可以让我们只关注我们关心的回调方法
		api.login("username", "password", new CallbackAdapter<Customer>() {
			@Override
			public void onSuccess(Customer result) {
				// 实际上如果用的是AndroidClient的话这里就是主线程即UI线程
				System.out.println("实际请求线程ID:" + Thread.currentThread().getId());
				// 解开锁让 JUnit继续
				synchronized (lock) {
					lock.notifyAll();
				}
			}
		});
		// 因为这是异步的所以要让JUnit的线程在这里等待
		synchronized (lock) {
			lock.wait();
		}
	}

}

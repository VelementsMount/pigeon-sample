package payne.framework.pigeon.sample.get;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import payne.framework.pigeon.client.Client;
import payne.framework.pigeon.core.Invocation;
import payne.framework.pigeon.server.BlockingInvocationContext;
import payne.framework.pigeon.server.InvocationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GetableAPITests {

	public static void main(String[] args) throws Exception {
		InvocationContext context = new BlockingInvocationContext();
		context.register(new GetableAPIImpl());
		context.bind(9090);
		context.startup();
		// 试试通过浏览器访问路径:http://localhost:9090/GetableAPIImpl/GetableAPI/login?username=zhangsan&password=123456
		// 或者http://localhost:9090/GetableAPIImpl/GetableAPI/loginByMap?argument0.username=zhangsan&argument0.password=123456
	}

	private InvocationContext context;
	private GetableAPI api;

	@Before
	public void initialize() throws Exception {
		context = new BlockingInvocationContext();
		context.register(new GetableAPIImpl());
		context.bind(9090);
		context.startup();

		Client client = new Client("localhost", 9090);
		api = client.create("HTTP/1.1", "application/json", "/GetableAPIImpl", GetableAPI.class);
	}

	@After
	public void destroy() throws Exception {
		context.shutdown();
	}

	@Test
	public void testLogin() throws Exception {
		boolean logined = api.login("zhangsan", "123456");
		Assert.assertEquals(true, logined);
	}

	@Test
	public void testLoginByMap() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "zhangsan");
		map.put("password", "123456");
		boolean logined = api.login(map);
		Assert.assertEquals(true, logined);
	}

	@Test
	public void testHttpURLConnection() throws Exception {
		// 通过默认的get方式请求,这就像是浏览器访问一样
		HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:9090/GetableAPIImpl/GetableAPI/login?username=zhangsan&password=123456").openConnection();
		// 获取Content-Length
		int length = connection.getContentLength();
		byte[] bytes = new byte[length];
		connection.getInputStream().read(bytes);
		String response = new String(bytes);
		// 实际上response就是一段json,而且反序列化过后类型是Boolean.class
		boolean logined = new ObjectMapper().readValue(response, Boolean.class);
		Assert.assertEquals(true, logined);
	}

	/**
	 * 实际上通过一般rest的框架也可以对框架发起请求可接收回应,像Spring的RestTemplate.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRestTemplateWithGET() throws Exception {
		RestTemplate rest = new RestTemplate();
		ResponseEntity<Boolean> response = rest.getForEntity("http://localhost:9090/GetableAPIImpl/GetableAPI/login?username=zhangsan&password=123456", Boolean.class);
		boolean logined = response.getBody();
		Assert.assertEquals(true, logined);
	}

	/**
	 * 不单只是可以通过get方式请求,实际上post请求也是可以的,不过这里要注意的是用post请求的时候必须了解框架对每次请求和回应的封装类型
	 * {@link Invocation}.因为框架把请求当做一次调用{@link Invocation}
	 * 回应也是直接通过这个类型的对象来的.所以采用post请求的时候就需要通过这个类型来构造参数和接收回应了
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRestTemplateWithPOST() throws Exception {
		RestTemplate rest = new RestTemplate();
		// 构造请求对象
		Invocation request = new Invocation();
		// 我们知道这个login方法需要两个String类型的参数
		request.setArguments(new String[] { "payne", "123456" });
		// 发起post请求,而且要清楚回应的类型也是Invocation
		ResponseEntity<Invocation> entity = rest.postForEntity("http://localhost:9090/GetableAPIImpl/GetableAPI/login", request, Invocation.class);
		// 主体对象就是框架实际返回的invocation
		Invocation response = entity.getBody();
		// 里面有个result属性就是调用的结果又因为我们实际上知道这个返回类型是Boolean类型所以
		Assert.assertEquals(true, response.getResult());
	}
}

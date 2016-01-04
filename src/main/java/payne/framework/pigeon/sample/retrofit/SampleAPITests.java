package payne.framework.pigeon.sample.retrofit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import payne.framework.pigeon.generation.objc.ObjectiveCGeneratorService;
import payne.framework.pigeon.server.BlockingInvocationContext;
import payne.framework.pigeon.server.InvocationContext;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import com.google.gson.Gson;

public class SampleAPITests {
	private InvocationContext context;
	private RetrofitSampleAPI api = new RestAdapter.Builder().setConverter(new GsonConverter(new Gson())).setEndpoint("http://localhost:9090").build().create(RetrofitSampleAPI.class);

	public static void main(String[] args) throws Exception {
		InvocationContext context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new SampleAPIImpl());
		context.register(new ObjectiveCGeneratorService(false));
		context.startup();
	}

	@Before
	public void initialize() throws Exception {
		context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new SampleAPIImpl());
		context.startup();
	}

	@After
	public void destroy() throws Exception {
		context.shutdown();
	}

	@Test
	public void testHeartbeat() throws Exception {
		System.out.println(api.heartbeat());
	}

	@Test
	public void testRegister() throws Exception {
		System.out.println(api.register(new User("payne", "123456")));
	}

	@Test
	public void testLogin() throws Exception {
		System.out.println(api.login("payne", "123456"));
	}

	@Test
	public void testLoginByForm() throws Exception {
		System.out.println(api.loginByForm("payne", "123456"));
	}

	@Test
	public void testMakefriend() throws Exception {
		api.makefriend(new User("a", "a"), new User("b", "b"));
	}

}

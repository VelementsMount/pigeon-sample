package payne.framework.pigeon.sample.restful;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import payne.framework.pigeon.server.BlockingInvocationContext;
import payne.framework.pigeon.server.InvocationContext;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import com.google.gson.Gson;

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
 * @date 2016年1月5日 下午2:47:31
 *
 * @version 1.0.0
 */
public class RestfulAPITests {
	private InvocationContext context;
	private RetrofitRestfulAPI api = new RestAdapter.Builder().setConverter(new GsonConverter(new Gson())).setEndpoint("http://localhost:9090").build().create(RetrofitRestfulAPI.class);

	public static void main(String[] args) throws Exception {
		InvocationContext context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new RestfulAPIImpl());
		context.startup();
	}

	@Before
	public void initialize() throws Exception {
		context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new RestfulAPIImpl());
		context.startup();
	}

	@After
	public void destroy() throws Exception {
		context.shutdown();
	}

	@Test
	public void testUpdate() throws Exception {
		Article article = new Article("琼瑶", "还珠格格", "");
		Assert.assertTrue(api.update(article));
	}

}

package payne.framework.pigeon.sample.httpclient;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

import junit.framework.Assert;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import payne.framework.pigeon.client.Client;
import payne.framework.pigeon.server.InvocationContext;
import payne.framework.pigeon.server.bio.BlockingInvocationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
public class ForumAPITests {
	private InvocationContext context;
	private ForumAPI api;

	public static void main(String[] args) throws Exception {
		InvocationContext context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new ForumAPIImpl());
		context.startup();
	}

	@Before
	public void initialize() throws Exception {
		context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new ForumAPIImpl());
		context.startup();

		Client client = new Client("localhost", 9090);
		api = client.create("HTTP", "application/json", "/ForumAPIImpl", ForumAPI.class);
	}

	@After
	public void destroy() throws Exception {
		context.shutdown();
	}

	@Test
	public void testEncode() throws Exception {
		String encoded = URLEncoder.encode("/articles/page={1:\\d+}/size={2:\\d+}", Charset.defaultCharset().name());
		String decoded = URLDecoder.decode(encoded, Charset.defaultCharset().name());
		System.out.println(decoded);
	}

	/**
	 * 我们注意到后台开放的list方法是GET方式的,所以我们也用GET的方式访问
	 * 
	 * @throws Exception
	 */
	@Test
	public void testListArticles() throws Exception {
		int page = 2;
		int size = 20;

		// 框架客户端方案
		List<Article> articles = api.listArticles(page, size);

		Assert.assertNotNull(articles);
		Assert.assertEquals(20, articles.size());
		System.out.println(articles);

		// http client 方案
		HttpClient client = new DefaultHttpClient();
		// 这里的路径 /ForumAPIImpl是指实现类 /ForumAPI是指接口 /articles/page=\\d+/size=\\d+是指方法,
		// 实际上都是可以通过 在实现类/接口/方法上加上@Open注解来进行配置, 只不过框架在默认状态下取它的名称作为路径
		HttpGet get = new HttpGet("http://localhost:9090/ForumAPIImpl/ForumAPI/articles/page=" + page + "/size=" + size);
		HttpResponse response = client.execute(get);
		// 检查结果是否在我们期望值中
		if (response.getStatusLine().getStatusCode() < 200 || response.getStatusLine().getStatusCode() >= 300) {
			throw new IOException("server response an unexpected status code : " + response.getStatusLine().getStatusCode() + " and message : " + response.getStatusLine().getReasonPhrase());
		}

		// 检查数据格式
		if ("application/json".equalsIgnoreCase(response.getEntity().getContentType().getValue()) == false) {
			// do some thing...
		}

		// json 反序列化
		ObjectMapper mapper = new ObjectMapper();
		// 注意这里的TypeReference是一个匿名内部类
		articles = mapper.readValue(response.getEntity().getContent(), new TypeReference<List<Article>>() {
		});
		Assert.assertNotNull(articles);
		Assert.assertEquals(20, articles.size());
		System.out.println(articles);
	}

	@Test
	public void testFindArticle() throws Exception {
		Long id = 10L;

		// http client 方案
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet("http://localhost:9090/ForumAPIImpl/ForumAPI/article/" + id);
		HttpResponse response = client.execute(get);

		// 检查结果是否在我们期望值中
		if (response.getStatusLine().getStatusCode() < 200 || response.getStatusLine().getStatusCode() >= 300) {
			throw new IOException("server response an unexpected status code : " + response.getStatusLine().getStatusCode() + " and message : " + response.getStatusLine().getReasonPhrase());
		}

		// 检查数据格式
		if ("application/json".equalsIgnoreCase(response.getEntity().getContentType().getValue()) == false) {
			// do some thing...
		}

		// json 反序列化
		ObjectMapper mapper = new ObjectMapper();
		// 注意这里的TypeReference是一个匿名内部类
		Article article = mapper.readValue(response.getEntity().getContent(), new TypeReference<Article>() {
		});
		Assert.assertNotNull(article);
		System.out.println(article);
	}

	@Test
	public void testDeletedArticle() throws Exception {
		HttpClient client = new DefaultHttpClient();
		Long id = 10L;
		HttpDelete delete = new HttpDelete("http://localhost:9090/ForumAPIImpl/ForumAPI/article/" + id);
		HttpResponse response = client.execute(delete);

		// 检查结果是否在我们期望值中
		if (response.getStatusLine().getStatusCode() < 200 || response.getStatusLine().getStatusCode() >= 300) {
			throw new IOException("server response an unexpected status code : " + response.getStatusLine().getStatusCode() + " and message : " + response.getStatusLine().getReasonPhrase());
		}

		// 检查数据格式
		if ("application/json".equalsIgnoreCase(response.getEntity().getContentType().getValue()) == false) {
			// do some thing...
		}

		// json 反序列化
		ObjectMapper mapper = new ObjectMapper();
		// 注意这里的TypeReference是一个匿名内部类
		Boolean success = mapper.readValue(response.getEntity().getContent(), new TypeReference<Boolean>() {
		});
		Assert.assertTrue(success);
	}

	@Test
	public void testSaveArticle() throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://localhost:9090/ForumAPIImpl/ForumAPI/article");
		// 这句很重要, 告诉服务器数据格式是json
		post.setHeader("Content-Type", "application/json");

		Article article = new Article(null, "习大大", "反腐成效", "...");
		byte[] bytes = mapper.writeValueAsBytes(article);

		HttpEntity entity = new ByteArrayEntity(bytes);
		post.setEntity(entity);
		HttpResponse response = client.execute(post);

		// 检查结果是否在我们期望值中
		if (response.getStatusLine().getStatusCode() < 200 || response.getStatusLine().getStatusCode() >= 300) {
			throw new IOException("server response an unexpected status code : " + response.getStatusLine().getStatusCode() + " and message : " + response.getStatusLine().getReasonPhrase());
		}

		// 检查数据格式
		if ("application/json".equalsIgnoreCase(response.getEntity().getContentType().getValue()) == false) {
			// do some thing...
		}

		// 注意这里的TypeReference是一个匿名内部类
		Article saved = mapper.readValue(response.getEntity().getContent(), new TypeReference<Article>() {
		});
		Assert.assertNotNull(saved);
		System.out.println(saved);
	}

	@Test
	public void testUpdateArticle() throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		HttpClient client = new DefaultHttpClient();
		HttpPut put = new HttpPut("http://localhost:9090/ForumAPIImpl/ForumAPI/article");
		// 这句很重要, 告诉服务器数据格式是json
		put.setHeader("Content-Type", "application/json");

		Article article = new Article(10L, "习大大", "反腐成效", "...");
		byte[] bytes = mapper.writeValueAsBytes(article);

		HttpEntity entity = new ByteArrayEntity(bytes);
		put.setEntity(entity);
		HttpResponse response = client.execute(put);

		// 检查结果是否在我们期望值中
		if (response.getStatusLine().getStatusCode() < 200 || response.getStatusLine().getStatusCode() >= 300) {
			throw new IOException("server response an unexpected status code : " + response.getStatusLine().getStatusCode() + " and message : " + response.getStatusLine().getReasonPhrase());
		}

		// 检查数据格式
		if ("application/json".equalsIgnoreCase(response.getEntity().getContentType().getValue()) == false) {
			// do some thing...
		}

		// 注意这里的TypeReference是一个匿名内部类
		Boolean success = mapper.readValue(response.getEntity().getContent(), new TypeReference<Boolean>() {
		});
		Assert.assertTrue(success);
	}

}

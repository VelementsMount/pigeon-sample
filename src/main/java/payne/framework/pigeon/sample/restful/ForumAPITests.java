package payne.framework.pigeon.sample.restful;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import payne.framework.pigeon.server.InvocationContext;
import payne.framework.pigeon.server.bio.BlockingInvocationContext;
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
public class ForumAPITests {
	private InvocationContext context;
	private RetrofitForumAPI api = new RestAdapter.Builder().setConverter(new GsonConverter(new Gson())).setEndpoint("http://localhost:9090").build().create(RetrofitForumAPI.class);

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
	}

	@After
	public void destroy() throws Exception {
		context.shutdown();
	}

	@Test
	public void testListArticles() throws Exception {
		List<Article> articles = api.listArticles(3, 20);
		Assert.assertEquals(20, articles.size());
	}

	@Test
	public void testFindArticle() throws Exception {
		Article article = api.findArticle(10L);
		Assert.assertNotNull(article);
	}

	@Test
	public void testDeletedArticle() throws Exception {
		Assert.assertTrue(api.deleteArticle(12L));
	}

	@Test
	public void testSaveArticle() throws Exception {
		Article article = api.saveArticle(new Article(null, "琼瑶", "还珠格格", ""));
		Assert.assertNotNull(article);
	}

	@Test
	public void testUpdateArticle() throws Exception {
		Article article = new Article(2L, "琼瑶", "还珠格格", "");
		Assert.assertTrue(api.updateArticle(article));
	}

}

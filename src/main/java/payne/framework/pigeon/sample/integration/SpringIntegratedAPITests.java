package payne.framework.pigeon.sample.integration;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import payne.framework.pigeon.client.Client;

/**
 * 通过继承{@link AbstractJUnit4SpringContextTests}
 * 和配置上spring的配置文件,就能在单元测试启动的时候把spring也启动起来.
 * 实际上这个也就是spring-test模块提供的单元测试功能,因为这种方式很适合用来给框架的开放服务做单元测试所以顺便提一下.
 * 
 * @author yangchangpei
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringIntegratedAPITests extends AbstractJUnit4SpringContextTests {

	private SpringIntegratedAPI api;

	/**
	 * 初始化的方法中把客户端构建好,生成api接口的代理对象,以后的单元测试方法直接用这个代理对象就行了.
	 * 
	 * @throws Exception
	 */
	@Before
	public void initialize() throws Exception {
		Client client = new Client("localhost", 4567);
		api = client.create("HTTP/1.1", "application/hessian", "/SpringIntegratedAPIImpl", SpringIntegratedAPI.class);
	}

	@Test
	public void testAddProduct() throws Exception {
		Product product = new Product();
		product.setProductName("iPhone 6 plus");
		product.setPrice(5000.0f);
		Long id = api.addProduct(product);
		Assert.assertNotNull(id);
	}

	@Test
	public void testGetProduct() throws Exception {
		Product product = api.getProduct(10L);
		Assert.assertNotNull(product);
	}

}

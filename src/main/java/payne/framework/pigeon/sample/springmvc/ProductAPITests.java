package payne.framework.pigeon.sample.springmvc;

import org.junit.Before;
import org.junit.Test;

import payne.framework.pigeon.client.Client;

public class ProductAPITests {

	private ProductAPI api;

	@Before
	public void setup() throws Exception {
		Client client = new Client("localhost", 8080);
		api = client.create("HTTP", "application/json", "/", ProductAPI.class);
	}

	@Test
	public void testList() throws Exception {
		System.out.println(api.list(new Pagination(1, 10)));
	}

}

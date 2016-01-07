package payne.framework.pigeon.sample.file;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import payne.framework.pigeon.client.Client;
import payne.framework.pigeon.core.Document;
import payne.framework.pigeon.server.InvocationContext;
import payne.framework.pigeon.server.bio.BlockingInvocationContext;

public class FileAPITests {
	private InvocationContext context;
	private FileAPI api;

	public static void main(String[] args) throws Exception {
		BlockingInvocationContext context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new FileAPIImpl());
		context.startup();
	}

	@Before
	public void initialize() throws Exception {
		context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new FileAPIImpl());
		context.startup();

		Thread.sleep(10);

		Client client = new Client("localhost", 9090);
		api = client.create("HTTP/1.0", "application/json", "/FileAPIImpl", FileAPI.class);
	}

	@After
	public void destroy() throws Exception {
		context.shutdown();
	}

	@Test
	public void testUpload() throws Exception {
		URL url = this.getClass().getResource("/picture.jpg");
		api.upload(new Document(url.toURI()));
	}

	@Test
	public void testDownload() throws Exception {
		Document document = api.download();
		System.out.println("Client received file " + document);
	}

}

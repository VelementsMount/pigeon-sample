package payne.framework.pigeon.sample.file;

import java.net.URL;

import payne.framework.pigeon.core.Document;

public class FileAPIImpl implements FileAPI {

	public void upload(Document file) throws Exception {
		System.out.println("Server received a file " + file);
	}

	public Document download() throws Exception {
		URL url = this.getClass().getResource("/picture.jpg");
		return new Document(url.toURI());
	}

}

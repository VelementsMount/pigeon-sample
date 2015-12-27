package payne.framework.pigeon.sample.extension;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * Description:为了简单起见,直接将缓存在{@Link ByteArrayOutputStream}
 * 里然后读取到数据最后的时候判断是否有敏感字符<br/>
 * 当然真正的敏感字符检查不可能这么简单,而且这样内存也吃不消.不过这个例子主要不是将如果判断是否有敏感字符而是如何用注解对框架进行拓展.
 * </p>
 * 
 * <p>
 * Company: 广州市俏狐信息科技有限公司
 * </p>
 * 
 * @author yangchangpei 646742615@qq.com
 *
 * @date 2015年7月25日 下午5:55:15
 *
 * @version 1.0.0
 */
public class SensitiveInputStream extends InputStream {
	private final InputStream inputStream;
	private final List<String> sensitives;

	private final ByteArrayOutputStream buffer;

	public SensitiveInputStream(InputStream inputStream, List<String> sensitives) {
		super();
		this.inputStream = inputStream;
		this.sensitives = sensitives;
		this.buffer = new ByteArrayOutputStream();
	}

	private void validate() throws IOException {
		String content = buffer.toString();
		for (String sensitive : sensitives) {
			if (content.contains(sensitive)) {
				throw new IOException("data contains sensitive words : " + sensitive);
			}
		}
		buffer.reset();
	}

	@Override
	public int read() throws IOException {
		int b = inputStream.read();
		// 如果读完了那么开始判断是否有敏感字符
		if (b == -1) {
			validate();
		}
		buffer.write(b);
		return b;
	}

	@Override
	public int read(byte[] b) throws IOException {
		return read(b, 0, b.length);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		int l = inputStream.read(b, off, len);
		// 如果读完了那么开始判断是否有敏感字符
		if (l == -1) {
			validate();
		}
		buffer.write(b, off, len);
		return l;
	}

	@Override
	public void close() throws IOException {
		try {
			validate();
		} finally {
			inputStream.close();
		}
	}

}

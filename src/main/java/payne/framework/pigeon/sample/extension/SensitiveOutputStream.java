package payne.framework.pigeon.sample.extension;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * <p>
 * Description:为了简单起见,直接将缓存在{@Link ByteArrayOutputStream}每次
 * {@link OutputStream#flush()}
 * 的时候就检查一次,这个逻辑明显是还有漏洞的,不过这个例子主要不是将如果判断是否有敏感字符而是如何用注解对框架进行拓展.
 * </p>
 * 
 * <p>
 * Company: 广州市俏狐信息科技有限公司
 * </p>
 * 
 * @author yangchangpei 646742615@qq.com
 *
 * @date 2015年7月25日 下午6:07:38
 *
 * @version 1.0.0
 */
public class SensitiveOutputStream extends OutputStream {

	private final OutputStream outputStream;
	private final List<String> sensitives;

	private final ByteArrayOutputStream buffer;

	public SensitiveOutputStream(OutputStream outputStream, List<String> sensitives) {
		super();
		this.outputStream = outputStream;
		this.sensitives = sensitives;
		this.buffer = new ByteArrayOutputStream();
	}

	@Override
	public void write(int b) throws IOException {
		outputStream.write(b);
		buffer.write(b);
	}

	@Override
	public void write(byte[] b) throws IOException {
		write(b, 0, b.length);
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		outputStream.write(b, off, len);
		buffer.write(b, off, len);
	}

	@Override
	public void flush() throws IOException {
		String content = buffer.toString();
		for (String sensitive : sensitives) {
			if (content.contains(sensitive)) {
				throw new IOException("data contains sensitive words : " + sensitive);
			}
		}
		buffer.reset();
		outputStream.flush();
	}

	@Override
	public void close() throws IOException {
		try {
			outputStream.flush();
		} finally {
			outputStream.close();
		}
	}

}

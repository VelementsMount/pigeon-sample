package payne.framework.pigeon.sample.extension;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import payne.framework.pigeon.core.Constants;
import payne.framework.pigeon.core.Header;
import payne.framework.pigeon.core.annotation.Process;
import payne.framework.pigeon.core.factory.bean.BeanFactory;
import payne.framework.pigeon.core.factory.stream.StreamFactory;
import payne.framework.pigeon.core.processing.Procedure;
import payne.framework.pigeon.core.protocol.Channel;
import payne.framework.pigeon.core.toolkit.IOToolkit;

/**
 * <p>
 * Description:框架把每一次请求或者回应都看做是对数据的一次流水线处理,而每一次数据变换如加密,压缩,校验,签名等都是这流水线上的一道工序,
 * 每一个注解代表一道工序,而这个类就是敏感词语检查工序的逻辑实现类.只要在初始化的时候把敏感词语从{@link Sensitive#value()}
 * 指定的配置文件中读取出来. 然后把输入输出流用自定义的带有敏感词语检查的输入输出流将原来的流包装一下即可实现敏感词语检查功能.
 * </p>
 * 
 * <p>
 * Company: 广州市俏狐信息科技有限公司
 * </p>
 * 
 * @author yangchangpei 646742615@qq.com
 *
 * @date 2015年7月25日 下午4:43:27
 *
 * @version 1.0.0
 */
public class InvocationSensitiveProcedure implements Procedure<Sensitive> {
	private List<String> sensitives = new ArrayList<String>();

	/**
	 * 这里需要明确这方法的几个参数分别代表什么?
	 * 
	 * @param side
	 *            当前是在客户端还是服务器, side == {@link Constants#SIDE_CLIENT}
	 *            时即当前是客户端,side == {@link Constants#SIDE_SERVER}时即当前是服务端
	 * @param process
	 *            在这里就是{@link Sensitive}注解上的{@link Process}注解实例
	 * @param annotation
	 *            就是当前调用的开放方法的{@link Sensitive}注解实例
	 */
	public void initialize(int side, Process process, Sensitive annotation, BeanFactory beanFactory, StreamFactory streamFactory, Header header, Channel channel) throws Exception {
		// 因为敏感字符会随着时间的变化而变化,所以敏感字符应该是可配置的,在这里我们读取这份敏感字符的配置文件
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(annotation.value().trim());
		// 简单的以行来拆分敏感词语
		String line = null;
		while ((line = IOToolkit.readLine(inputStream)) != null) {
			// 忽略空行
			if (line.trim().equals("")) {
				continue;
			}
			// 以#开始的看做是注释
			if (line.trim().startsWith("#")) {
				continue;
			}
			sensitives.add(line);
		}
	}

	/**
	 * 如果当前是在客户端的时候,这个方法在客户端请求的时候被框架调用. 如果当前是在服务端的时候,这个方法在服务端对请求进行回应时调用.
	 */
	public OutputStream wrap(int side, Process process, Sensitive annotation, BeanFactory beanFactory, StreamFactory streamFactory, Channel channel, Header header, OutputStream outputStream) throws Exception {
		// 用敏感词语输出流对输出流进行包装
		return new SensitiveOutputStream(outputStream, sensitives);
	}

	/**
	 * 如果当前是在客户端的时候,这个方法在客户端读取服务端回应数据时被框架调用. 如果当前是在服务端的时候,这个方法在服务端对读取客户端请求时调用.
	 */
	public InputStream wrap(int side, Process process, Sensitive annotation, BeanFactory beanFactory, StreamFactory streamFactory, Channel channel, Header header, InputStream inputStream) throws Exception {
		// 用敏感词语输入流对输入流进行包装
		return new SensitiveInputStream(inputStream, sensitives);
	}

}

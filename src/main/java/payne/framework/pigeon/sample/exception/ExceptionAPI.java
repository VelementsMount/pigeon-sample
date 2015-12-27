package payne.framework.pigeon.sample.exception;

import payne.framework.pigeon.core.Document;
import payne.framework.pigeon.core.annotation.Open;
import payne.framework.pigeon.core.exception.CodedException;

/**
 * 作为一个框架来说,稳定不出异常当然是最重要的,但是我们都知道异常是不可避免的,而且框架是通过网络调用所以总会有不可抗拒的异常,例如断网,超时,防火墙等等.
 * 况且异常原本就属于功能开发中不可或缺的一部分.
 * 框架采用HTTP作为传输协议,也遵循HTTP的协议即正常的情况下返回的状态码为200,出错的情况下状态码为400,为了让使用者可以拓展这个状态码,
 * 框架实现了一个抽象的异常类型{@link CodedException},只要在开放的方法里或拦截器还是过滤器等地方抛出这种类型的异常,框架就会以
 * {@link CodedException#getCode()}作为回应的状态码.因为HTTP协议里状态码后面还可以跟一小段消息,例如
 * <p>
 * HTTP/1.1 200 OK
 * </p>
 * <p>
 * HTTP/1.1 404 Not Found
 * </p>
 * 这个消息来源于{@link CodedException#getReason()} 这个时候假如我们想在用户执行未授权的功能的时候就可以抛出一个
 * {@link UnauthorizedException}类型的异常对象客户端就可以知道当前访问路径还没有被授权访问
 * 
 * @see UnauthorizedException
 * 
 * @author yangchangpei
 *
 */
@Open
public interface ExceptionAPI {

	/**
	 * 这里以一个下载文件作为例子,因为很有可能用户需要有权限判断是否能下载某个文件,假如用户没有权限,那么直接抛出
	 * {@link UnauthorizedException}即可
	 * 
	 * @param path
	 *            文件路径
	 * @return 指定的文件
	 * @throws Exception
	 */
	Document download(String path) throws Exception;

}

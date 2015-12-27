package payne.framework.pigeon.sample.exception;

import payne.framework.pigeon.core.exception.CodedException;

/**
 * <p>
 * Description:用户未授权异常,当用户执行未授权的功能时可以抛出该异常,当这个异常被抛出的时候,客户端就能收到
 * {@link UnauthorizedException#getCode()}状态码和
 * {@link UnauthorizedException#getReason()}消息
 * </p>
 * 
 * <p>
 * Company: 广州市俏狐信息科技有限公司
 * </p>
 * 
 * @author yangchangpei 646742615@qq.com
 *
 * @date 2015年7月25日 下午3:12:57
 *
 * @version 1.0.0
 */
public class UnauthorizedException extends CodedException {
	private static final long serialVersionUID = -5257015852750341791L;

	public UnauthorizedException() {
		super();
	}

	public UnauthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnauthorizedException(String message) {
		super(message);
	}

	public UnauthorizedException(Throwable cause) {
		super(cause);
	}

	/**
	 * HTTP协议里定义未授权的状态码为401,所以自然这里返回401即可
	 */
	@Override
	public int getCode() {
		return 401;
	}

	/**
	 * HTTP协议里未授权的消息未Unauthorized,那么直接返回即可
	 */
	@Override
	public String getReason() {
		return "Unauthorized";
	}

}

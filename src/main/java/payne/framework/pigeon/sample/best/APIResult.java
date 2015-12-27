package payne.framework.pigeon.sample.best;

import java.io.Serializable;

/**
 * 所有开放接口返回值类型的超类<br/>
 * 虽然框架对返回值类型不做要求,但是这是在针对框架做开发的时候总结的一下最佳实践.定义一个返回值类型的超类是有很多好处的,<br/>
 * 例如在做拦截器功能的时候,可以统一代码去检查请求时候成功.<br/>
 * 代码结构统一容易拓展等等
 * 
 * @author Payne
 *
 */
public class APIResult implements Serializable {
	private static final long serialVersionUID = 6885018215642881186L;

	private boolean success;
	private String message;

	public APIResult() {
		this(true, "操作成功!");
	}

	public APIResult(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

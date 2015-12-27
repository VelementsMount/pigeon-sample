package payne.framework.pigeon.sample.interception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import payne.framework.pigeon.core.annotation.Intercept;

/**
 * 通过注解{@link LogInterceptor}
 * 的value属性可以配置一个或多个拦截器,注意每个拦截器的顺序,框架将会严格按照你定义的顺序进行层层调用拦截.<br/>
 * 另外一个高级功能是因为实现类可能继承了另一个类,而那个类上可能有声明了拦截器,可以通过{@link Intercept#inherit()}<br/>
 * 属性指定是否继承父类的拦截器<br/>
 * 在这个例子我展示一个日志拦截器
 * 
 * @author yangchangpei
 *
 */
@Intercept(value = { LogInterceptor.class }, inherit = false)
public class InterceptedAPIImpl implements InterceptedAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void foo() throws Exception {
		logger.info("method foo executed");
	}

	public void bar() throws Exception {
		logger.info("method bar executed");
	}

}

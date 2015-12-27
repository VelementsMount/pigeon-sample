package payne.framework.pigeon.sample.filtration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import payne.framework.pigeon.core.filtration.Filter;
import payne.framework.pigeon.core.filtration.FilterChain;
import payne.framework.pigeon.core.protocol.Channel;
import payne.framework.pigeon.server.InvocationContext;
import payne.framework.pigeon.server.firewall.BlacklistFilter;
import payne.framework.pigeon.server.firewall.ThrottleFilter;

/**
 * <p>
 * Description: 简单的日志过滤器,只需要在启动{@link InvocationContext}
 * 的时候把拦截器添加进去就能在每次客户端请求连接的时候进入该过滤器,框架提供了两个安全过滤器{@link ThrottleFilter}和
 * {@link BlacklistFilter}
 * </p>
 * 
 * @see ThrottleFilter
 * @see BlacklistFilter
 * 
 *      <p>
 *      Company: 广州市俏狐信息科技有限公司
 *      </p>
 * 
 * @author yangchangpei 646742615@qq.com
 *
 * @date 2015年7月25日 下午3:55:23
 *
 * @version 1.0.0
 */
public class LogFilter implements Filter<Channel> {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 和{@link javax.servlet.Filter}一样,需要通过调用过滤链的
	 * {@link javax.servlet.FilterChain#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse)}
	 * 方法,请求才会继续往下走
	 */
	public void filtrate(Channel target, FilterChain<Channel> chain) throws Exception {
		logger.info("request start " + target.getPath());
		chain.go(target);
		logger.info("request end " + target.getPath());
	}

}

package payne.framework.pigeon.sample.session;

import java.util.HashMap;
import java.util.Map;

import payne.framework.pigeon.client.cookie.CookieInterceptor;
import payne.framework.pigeon.core.Invocation;
import payne.framework.pigeon.core.annotation.Intercept;
import payne.framework.pigeon.server.session.Session;
import payne.framework.pigeon.server.session.SessionInterceptor;
import payne.framework.pigeon.server.session.SessionLocal;

/**
 * 服务端保存客户端的会话是通过http的cookie完成的,和普通的http协议cookie没区别.<br/>
 * 如果要实现类拥有session的功能需要客户端配合完成,即在实现类上加入{@link SessionInterceptor}拦截器<br/>
 * 同时客户端要加入{@link CookieInterceptor}拦截器,不要混淆...<br/>
 * 只需要配置好了,就能在实现类的开放方法中使用{@link SessionLocal#getCurrent()}方法获取当前的session<br/>
 * 前提是这是通过远程调用的,是框架管理的.而不是项目里面内部调用的...<br/>
 * 在这里可以顺便说一下,通过拦截器获取到的当前调用实例{@link Invocation}可以通过其中的
 * {@link Invocation#getServerHeader()}<br/>
 * 属性写入一些cookie给客户端,让客户端下次来的时候带上.具体可以参考{@link SessionInterceptor}和
 * {@link CookieInterceptor}
 * 
 * @see SessionInterceptor
 * @see CookieInterceptor
 * 
 * @author yangchangpei
 *
 */
@Intercept({ SessionInterceptor.class })
public class SessionedAPIImpl implements SessionedAPI {

	public boolean login(String username, String password) throws Exception {
		Map<String, String> user = new HashMap<String, String>();
		user.put("username", username);
		user.put("password", password);
		// 获取当前请求的session
		Session session = SessionLocal.getCurrent();
		session.put("user", user);
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean logout() throws Exception {
		// 获取当前请求的session
		Session session = SessionLocal.getCurrent();

		Map<String, String> user = (Map<String, String>) session.get("user");

		System.err.println("当前用户 : " + user);

		// 这里证明没有登录
		if (user == null) {
			return false;
		}

		session.remove("user");

		return true;
	}

}

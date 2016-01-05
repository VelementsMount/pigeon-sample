package payne.framework.pigeon.sample.session;

import payne.framework.pigeon.client.cookie.CookieInterceptor;
import payne.framework.pigeon.core.annotation.Open;
import payne.framework.pigeon.core.annotation.Param;
import payne.framework.pigeon.server.session.SessionInterceptor;
import payne.framework.pigeon.server.session.SessionLocal;

/**
 * 开启session功能开放接口无需做任何特殊配置,只需要实现类加入{@link SessionInterceptor},<br/>
 * 客户端配合加入{@link CookieInterceptor}即可,服务端的实现类方法中通过 {@link SessionLocal#getCurrent()}即可获取当前的session
 * 
 * @author yangchangpei
 *
 */
@Open
public interface SessionedAPI {

	boolean login(@Param("username") String username, @Param("password") String password) throws Exception;

	boolean logout() throws Exception;

}

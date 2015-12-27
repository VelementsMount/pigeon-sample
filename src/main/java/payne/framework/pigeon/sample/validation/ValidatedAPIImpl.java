package payne.framework.pigeon.sample.validation;

import payne.framework.pigeon.core.annotation.Intercept;
import payne.framework.pigeon.core.validation.ValidationInterceptor;

/**
 * 实现类加入{@link ValidationInterceptor}拦截器,轻松实现数据校验功能.
 * 
 * @author yangchangpei
 *
 */
@Intercept({ ValidationInterceptor.class })
public class ValidatedAPIImpl implements ValidatedAPI {

	public User register(User user) throws Exception {
		user.setId(1L);
		return user;
	}

	public User login(String username, String password) throws Exception {
		return new User(1L, username, password);
	}

}

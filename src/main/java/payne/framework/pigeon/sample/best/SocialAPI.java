package payne.framework.pigeon.sample.best;

import payne.framework.pigeon.core.annotation.Open;
import payne.framework.pigeon.generation.annotation.Note;

@Open
public interface SocialAPI {

	@Note({ "注册用户" })
	RegisterResult register(String username, String password) throws Exception;

	@Note({ "用户登录" })
	LoginResult login(String username, String password) throws Exception;

}

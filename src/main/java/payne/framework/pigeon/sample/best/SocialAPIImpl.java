package payne.framework.pigeon.sample.best;

import org.springframework.stereotype.Service;

@Service
public class SocialAPIImpl implements SocialAPI {

	public RegisterResult register(String username, String password) throws Exception {
		User user = new User(username, password);
		user.setId(1L);
		RegisterResult result = new RegisterResult();
		result.setUser(user);
		return result;
	}

	public LoginResult login(String username, String password) throws Exception {
		User user = new User(username, password);
		user.setId(1L);
		LoginResult result = new LoginResult();
		result.setUser(user);
		return result;
	}

}

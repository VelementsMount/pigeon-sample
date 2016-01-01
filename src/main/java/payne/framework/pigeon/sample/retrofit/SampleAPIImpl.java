package payne.framework.pigeon.sample.retrofit;

public class SampleAPIImpl implements SampleAPI {

	@Override
	public void heartbeat() throws Exception {
		System.out.println("heartbeat");
	}

	@Override
	public boolean register(User user) throws Exception {
		System.out.println(user);
		return user.getUsername() != null && user.getPassword() != null;
	}

	@Override
	public User login(String username, String password) throws Exception {
		System.out.println("username = " + username + " ; password = " + password);
		return new User(username, password);
	}

}

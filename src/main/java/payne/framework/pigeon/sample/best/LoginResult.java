package payne.framework.pigeon.sample.best;

public class LoginResult extends APIResult {
	private static final long serialVersionUID = -4311438872290032549L;

	private User user;

	public LoginResult() {
		super();
	}

	public LoginResult(boolean success, String message) {
		super(success, message);
	}

	public LoginResult(boolean success, String message, User user) {
		super(success, message);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

package payne.framework.pigeon.sample.best;


public class RegisterResult extends APIResult {
	private static final long serialVersionUID = -7113697560705678742L;
	
	private User user;

	public RegisterResult() {
		super();
	}

	public RegisterResult(boolean success, String message) {
		super(success, message);
	}

	public RegisterResult(boolean success, String message, User user) {
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

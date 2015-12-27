package payne.framework.pigeon.sample.asynchronous;

import java.io.Serializable;

/**
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Company: 广州市俏狐信息科技有限公司
 * </p>
 * 
 * @author yangchangpei 646742615@qq.com
 *
 * @date 2015年8月27日 下午8:52:30
 *
 * @version 1.0.0
 */
public class Customer implements Serializable {
	private static final long serialVersionUID = -8818434182620431801L;

	private String username;
	private String password;

	public Customer() {
		super();
	}

	public Customer(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Customer [username=" + username + ", password=" + password + "]";
	}

}

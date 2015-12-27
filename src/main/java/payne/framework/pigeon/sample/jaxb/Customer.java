package payne.framework.pigeon.sample.jaxb;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 标注上{@link XmlRootElement}表明该类型可以通过jaxb的xml格式序列化
 * 
 * @author Payne
 *
 */
@XmlRootElement
public class Customer implements Serializable {
	private static final long serialVersionUID = -2788830129535620728L;

	private Long id;
	private String username;
	private String password;
	private String nickname;

	public Customer() {
		super();
	}

	public Customer(Long id, String username, String password, String nickname) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
	}

	public Long getId() {
		return id;

	}

	public void setId(Long id) {
		this.id = id;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", username=" + username + ", password=" + password + ", nickname=" + nickname + "]";
	}

}

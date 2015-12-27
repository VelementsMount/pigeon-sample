package payne.framework.pigeon.sample.validation;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * 培养好习惯,需要序列化的类都实现{@link Serializable}接口, 这里展示一个简单的对象属性校验的例子,你看到
 * {@link User#username}和{@link User#password}上都有{@link NotNull}
 * 注解,实际上就是要求这两个字段不能为空,当然还有很多其他用来校验的注解.
 * 
 * @author yangchangpei
 *
 */
public class User implements Serializable {
	private static final long serialVersionUID = 3509783664810457548L;

	private Long id;
	@NotNull
	private String username;
	@NotNull
	private String password;

	public User() {
		super();
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public User(Long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
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

}

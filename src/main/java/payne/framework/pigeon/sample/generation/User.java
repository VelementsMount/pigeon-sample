package payne.framework.pigeon.sample.generation;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import payne.framework.pigeon.generation.annotation.Note;

/**
 * 微博用户
 * 
 * @author yangchangpei
 *
 */
@Note("微博用户")
public class User implements Serializable {
	private static final long serialVersionUID = 5991214707724188798L;

	private Long id;
	private String username;
	private String password;
	private Set<User> fans = new HashSet<User>();
	private Set<User> enemies = new HashSet<User>();
	private String[] nicknames;
	private Gender gender;

	public User() {
		super();
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

	@Note("用户名")
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

	public Set<User> getFans() {
		return fans;
	}

	public void setFans(Set<User> fans) {
		this.fans = fans;
	}

	public String[] getNicknames() {
		return nicknames;
	}

	public void setNicknames(String[] nicknames) {
		this.nicknames = nicknames;
	}

	public Set<User> getEnemies() {
		return enemies;
	}

	public void setEnemies(Set<User> enemies) {
		this.enemies = enemies;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}

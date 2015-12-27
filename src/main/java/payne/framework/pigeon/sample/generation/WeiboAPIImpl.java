package payne.framework.pigeon.sample.generation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class WeiboAPIImpl implements WeiboAPI {

	public User login(String username, String password) throws Exception {
		User user = new User(1L, username, password);
		user.setGender(Gender.GRIL);
		User friend1 = new User(2L, "friend1", "friend1");
		friend1.setGender(Gender.GRIL);
		User friend2 = new User(3L, "friend2", "friend2");
		friend1.setFans(new HashSet<User>(Arrays.asList(friend2)));
		user.setFans(new HashSet<User>(Arrays.asList(friend1)));
		return user;
	}

	@Override
	public User[] fans() throws Exception {
		User friend1 = new User(2L, "friend1", "friend1");
		User friend2 = new User(3L, "friend2", "friend2");
		return new User[] { friend1, friend2 };
	}

	@Override
	public List<String> nicknames() throws Exception {
		return Arrays.asList("a", "b", "c");
	}

	@Override
	public void register(User user) throws Exception {
		System.out.println(user);
	}

	@Override
	public void makeFriends(List<User> frieids) throws Exception {
		System.out.println(frieids);
	}

	@Override
	public Map<String, User> search(Gender gender) throws Exception {
		User user1 = new User(2L, "friend1", "friend1");
		user1.setGender(gender);
		User user2 = new User(3L, "friend2", "friend2");
		user2.setGender(gender);

		Map<String, User> map = new HashMap<String, User>();
		map.put("a", user1);
		map.put("b", user2);

		return map;
	}

	@Override
	public void nickname(Map<String, User> nicknames) throws Exception {
		System.out.println(nicknames);
	}

}

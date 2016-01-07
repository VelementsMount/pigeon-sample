package payne.framework.pigeon.sample.generation;

import java.util.List;
import java.util.Map;
import payne.framework.pigeon.core.Callback;
import payne.framework.pigeon.core.annotation.Correspond;
import payne.framework.pigeon.core.annotation.Open;
import payne.framework.pigeon.generation.annotation.Name;
import payne.framework.pigeon.generation.annotation.Note;
import payne.framework.pigeon.sample.generation.Gender;
import payne.framework.pigeon.sample.generation.User;
import payne.framework.pigeon.sample.generation.WeiboAPI;

@Correspond(WeiboAPI.class)
@Open
@Note(value = {"微博API接口"})
public interface AsyncWeiboAPI {

	 void fans( Callback<User[]> callback);
	
	 void nickname(@Name(value = "nicknames") Map<String, User> nicknames,  Callback<Void> callback);
	
	 void makeFriends(@Name(value = "friends") List<User> friends,  Callback<Void> callback);
	
	 void search(@Name(value = "gender") Gender gender,  Callback<Map<String, User>> callback);
	
	 void nicknames( Callback<List<String>> callback);
	
	 void register(@Name(value = "user") User user,  Callback<Void> callback);
	
	@Note(value = {"登陆"})
	 void login(@Name(value = "username") String username, @Name(value = "password") String password,  Callback<User> callback);
	
}

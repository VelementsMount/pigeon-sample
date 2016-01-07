package payne.framework.pigeon.sample.generation;

import java.util.List;
import java.util.Map;
import payne.framework.pigeon.core.annotation.Correspond;
import payne.framework.pigeon.core.annotation.Open;
import payne.framework.pigeon.generation.annotation.Name;
import payne.framework.pigeon.generation.annotation.Note;
import payne.framework.pigeon.sample.generation.Gender;
import payne.framework.pigeon.sample.generation.User;
import payne.framework.pigeon.sample.generation.WeiboAPI;
import rx.Observable;

@Correspond(WeiboAPI.class)
@Open
@Note(value = {"微博API接口"})
public interface ReactiveWeiboAPI {

	 Observable<User[]> fans();
	
	 Observable<Void> nickname( @Name(value = "nicknames") Map<String, User> nicknames);
	
	 Observable<Void> makeFriends( @Name(value = "friends") List<User> friends);
	
	 Observable<Map<String, User>> search( @Name(value = "gender") Gender gender);
	
	 Observable<List<String>> nicknames();
	
	 Observable<Void> register( @Name(value = "user") User user);
	
	@Note(value = {"登陆"})
	 Observable<User> login( @Name(value = "username") String username, @Name(value = "password") String password);
	
}

package payne.framework.pigeon.sample.generation;

import java.util.List;
import java.util.Map;

import payne.framework.pigeon.core.annotation.Open;
import payne.framework.pigeon.generation.annotation.Ignore;
import payne.framework.pigeon.generation.annotation.Name;
import payne.framework.pigeon.generation.annotation.Note;

/**
 * 框架提供了java的服务端/客户端,只实现了objective-c的客户端,所以通过java服务端定义的接口生成objective-c <br/>
 * 端调用的接口这是一个重要的过程,这样就省了重复写接口的问题,而且是通过机器生成的,就能减少写错的现象.如果我们希望接口能够生成友好的objective-c <br/>
 * 接口那么我们还要用到另外两个注解.{@link Name},{@link Ignore}. <br/>
 * 框架不仅会生成这个开放接口上的所有开放方法,而且这些方法上的自定义类型参数或返回值类型也会对应生成.
 * 
 * @see {@link Name} 给参数命名
 * @see {@link Ignore} 忽略Model对象的某些属性
 * 
 * @author yangchangpei
 *
 */
@Open
@Note("微博API接口")
public interface WeiboAPI {

	/**
	 * 因为源文件编译成字节码文件后不会保留参数的名称(实际上是有办法拿到参数的名称的不过很麻烦), <br/>
	 * 所以框架提供一个折衷的方案让开发者指定生成方法时指定每个参数的名称,就是通过{@link Name}, <br/>
	 * 实际上不用这个注解也是不会影响功能的,框架会默认把参数名看作是arg0,arg1,arg2...这样看起来就不那么友好了. <br/>
	 * 所以建议在接口需要生成到别的平台时给方法的参数标注上{@link Name}注解指定参数名称.如果生成呢?请参考 {@link GenerationTests} <br/>
	 * 
	 * @see GenerationTests
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@Note("登陆")
	User login(@Name("username") String username, @Name("password") String password) throws Exception;

	User[] fans() throws Exception;

	List<String> nicknames() throws Exception;

	void register(@Name("user") User user) throws Exception;

	void makeFriends(@Name("friends") List<User> frieids) throws Exception;

	Map<String, User> search(@Name("gender") Gender gender) throws Exception;

	void nickname(@Name("nicknames") Map<String, User> nicknames) throws Exception;

}

package payne.framework.pigeon.sample.asynchronous;

import payne.framework.pigeon.core.Callback;
import payne.framework.pigeon.core.annotation.Open;
import payne.framework.pigeon.generation.annotation.Name;
import payne.framework.pigeon.sample.asynchronous.Customer;

/**
 * 
 * 功能:这就是我们生成的AsyncAPI异步接口
 * 
 * @author yangchangpei 646742615@qq.com
 *
 */
@Open(value = "/async")
public interface AsyncAsyncAPI {

	void login(@Name(value = "username") String username, @Name(value = "password") String password, Callback<Customer> callback);

}

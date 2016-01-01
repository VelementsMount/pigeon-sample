package payne.framework.pigeon.sample.retrofit;

import payne.framework.pigeon.core.annotation.Open;
import payne.framework.pigeon.core.annotation.rest.Param;

@Open
public interface SampleAPI {

	void heartbeat() throws Exception;

	boolean register(User user) throws Exception;

	User login(@Param("username") String username, @Param("password") String password) throws Exception;

}

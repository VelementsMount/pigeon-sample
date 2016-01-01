package payne.framework.pigeon.sample.retrofit;

import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface RetrofitSampleAPI {

	@GET("/SampleAPIImpl/SampleAPI/heartbeat")
	Object heartbeat();

	@POST("/SampleAPIImpl/SampleAPI/register")
	boolean register(@Body User user);

	@GET("/SampleAPIImpl/SampleAPI/login")
	User login(@Query("username") String username, @Query("password") String password);

	@FormUrlEncoded
	@POST("/SampleAPIImpl/SampleAPI/login")
	User loginByForm(@Field("username") String username, @Field("password") String password);

}

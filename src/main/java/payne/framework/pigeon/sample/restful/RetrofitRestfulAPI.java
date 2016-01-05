package payne.framework.pigeon.sample.restful;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Company: 广州市俏狐信息科技有限公司
 * </p>
 * 
 * @author yangchangpei 646742615@qq.com
 *
 * @date 2016年1月5日 下午2:48:32
 *
 * @version 1.0.0
 */
public interface RetrofitRestfulAPI {

	@POST("/RestfulAPIImpl/RestfulAPI/update")
	boolean update(@Body Article article);

	@GET("/RestfulAPIImpl/RestfulAPI/find/{name}")
	Article find(@Path("name") String name);

	@GET("/RestfulAPIImpl/RestfulAPI/list/{page}/{size}")
	List<Article> list(@Path("page") int page, @Path("size") int size);

}

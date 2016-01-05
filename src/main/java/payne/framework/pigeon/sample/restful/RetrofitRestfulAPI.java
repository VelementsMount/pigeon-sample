package payne.framework.pigeon.sample.restful;

import retrofit.http.Body;
import retrofit.http.POST;

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
	boolean update(@Body Article article) throws Exception;

}

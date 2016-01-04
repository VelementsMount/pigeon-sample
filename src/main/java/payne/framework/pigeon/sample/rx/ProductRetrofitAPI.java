package payne.framework.pigeon.sample.rx;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

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
 * @date 2016年1月3日 下午6:09:56
 *
 * @version 1.0.0
 */
public interface ProductRetrofitAPI {

	@GET("/ProductAPIImpl/ProductAPI/list")
	List<Product> listSynchronously(@Query("page") int page, @Query("size") int size);

	@GET("/ProductAPIImpl/ProductAPI/list")
	void listAsynchronously(@Query("page") int page, @Query("size") int size, Callback<List<Product>> callback);

	@GET("/ProductAPIImpl/ProductAPI/list")
	Observable<List<Product>> listObservablely(@Query("page") int page, @Query("size") int size);

}

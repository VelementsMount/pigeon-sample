package payne.framework.pigeon.sample.rx;

import java.util.List;

import payne.framework.pigeon.core.annotation.Open;
import payne.framework.pigeon.core.annotation.rest.Param;

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
 * @date 2016年1月3日 下午6:03:58
 *
 * @version 1.0.0
 */
@Open
public interface ProductAPI {

	List<Product> list(@Param("page") int page, @Param("size") int size) throws Exception;

}

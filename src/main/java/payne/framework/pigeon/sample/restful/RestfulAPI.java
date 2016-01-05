package payne.framework.pigeon.sample.restful;

import payne.framework.pigeon.core.annotation.Accept;
import payne.framework.pigeon.core.annotation.Accept.Mode;
import payne.framework.pigeon.core.annotation.Open;

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
 * @date 2016年1月5日 上午10:32:49
 *
 * @version 1.0.0
 */
@Open
public interface RestfulAPI {

	@Accept(modes = { Mode.POST }, media = { "application/json" })
	boolean update(Article article) throws Exception;

}

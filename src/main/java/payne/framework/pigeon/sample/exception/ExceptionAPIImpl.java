package payne.framework.pigeon.sample.exception;

import payne.framework.pigeon.core.Document;

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
 * @date 2015年7月21日 下午7:28:50
 *
 * @version 1.0.0
 */
public class ExceptionAPIImpl implements ExceptionAPI {

	/**
	 * 实现类只是始终抛出{@link UnauthorizedException}
	 */
	public Document download(String path) throws Exception {
		throw new UnauthorizedException("您没有权限下载 " + path + " 文件");
	}

}

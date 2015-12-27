package payne.framework.pigeon.sample.extension;

/**
 * <p>
 * Description:如果请求数据中带有敏感词语,请求是不会运行到这里的,在数据检查得时候就已经阻止了请求的继续执行.
 * </p>
 * 
 * <p>
 * Company: 广州市俏狐信息科技有限公司
 * </p>
 * 
 * @author yangchangpei 646742615@qq.com
 *
 * @date 2015年7月27日 上午11:00:49
 *
 * @version 1.0.0
 */
public class SensitiveAPIImpl implements SensitiveAPI {

	public void hello(String hello) throws Exception {
		System.out.println("服务端收到:" + hello);
	}

}

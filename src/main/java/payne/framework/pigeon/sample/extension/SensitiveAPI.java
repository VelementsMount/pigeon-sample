package payne.framework.pigeon.sample.extension;

import payne.framework.pigeon.core.annotation.Open;

/**
 * <p>
 * Description:带有敏感词语检查功能的开放接口,这个例子展示如何通过自定义注解对框架进行功能拓展,详细请看{@link Sensitive},
 * {@link InvocationSensitiveProcedure}
 * </p>
 * 
 * <p>
 * Company: 广州市俏狐信息科技有限公司
 * </p>
 * 
 * @author yangchangpei 646742615@qq.com
 *
 * @date 2015年7月27日 上午10:58:47
 *
 * @version 1.0.0
 */
@Open
public interface SensitiveAPI {

	@Sensitive
	void hello(String hello) throws Exception;

}

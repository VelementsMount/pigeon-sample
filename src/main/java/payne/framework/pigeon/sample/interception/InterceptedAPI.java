package payne.framework.pigeon.sample.interception;

import payne.framework.pigeon.core.annotation.Open;

/**
 * 开放接口上不能配置拦截器,必须在实现类上配置,因为接口是要打包发布的,拦截器只是对于后台内部使用,所以通过实现类来配置是比较合理的.
 * 
 * @author yangchangpei
 *
 */
@Open
public interface InterceptedAPI {

	public void foo() throws Exception;

	public void bar() throws Exception;

}

package payne.framework.pigeon.sample.advance;

import payne.framework.pigeon.core.annotation.Open;
import payne.framework.pigeon.server.AsynchronousNonBlockingInvocationContext;
import payne.framework.pigeon.server.BlockingInvocationContext;
import payne.framework.pigeon.server.InvocationContext;
import payne.framework.pigeon.server.NonBlockingInvocationContext;

/**
 * <p>
 * Description:实际上框架不仅对开放服务容器接口{@link InvocationContext}提供了传统的阻塞IO方式的
 * {@link BlockingInvocationContext}还有JDK1.4提供的非阻塞IO
 * {@link NonBlockingInvocationContext}同时还提供了JDK1.7发布的异步非阻塞IO
 * {@link AsynchronousNonBlockingInvocationContext}
 * 
 * 用这三种IO方式构建的开放服务容器对于客户端来说是透明的,调用的结果也是一致的.而区别在于服务端采取哪一种方式更合适.
 * 1.阻塞IO比较适合链接数量比较少而单个连接传送数据量比较大的需求<br/>
 * 2.同步非阻塞IO比较适合链接数量比较大而单个链接传送的数据量比较小的需求<br/>
 * 3.异步非阻塞IO实际上综合两者的优点实际上看来这个可以适合所有的需求但是要求JDK1.7以上所以这也是我们应该考虑的范围以内.
 * </p>
 * 
 * <p>
 * Company: 广州市俏狐信息科技有限公司
 * </p>
 * 
 * @author yangchangpei 646742615@qq.com
 *
 * @date 2015年8月28日 上午10:13:26
 *
 * @version 1.0.0
 * 
 * @see InvocationContext
 * @see BlockingInvocationContext
 * @see NonBlockingInvocationContext
 * @see AsynchronousNonBlockingInvocationContext
 * 
 */
@Open
public interface SameAPI {
	
	public String doTheSameThing(String thing) throws Exception;
}

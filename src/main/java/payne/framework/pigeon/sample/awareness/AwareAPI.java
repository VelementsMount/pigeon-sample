package payne.framework.pigeon.sample.awareness;

import org.springframework.context.ApplicationContextAware;

import payne.framework.pigeon.core.annotation.Open;
import payne.framework.pigeon.server.InvocationContextAware;

/**
 * 类似于Spring的{@link ApplicationContextAware},框架也提供一个框架启动完成的通知接口
 * {@link InvocationContextAware}
 * ,只要接口继承或实现类实现这个接口(一般来说让实现类实现这个接口会比较好点,因为接口要打包给客户端的). 就能在框架启动完成后接收到通知
 * 
 * @author yangchangpei
 *
 */
@Open
public interface AwareAPI {

}

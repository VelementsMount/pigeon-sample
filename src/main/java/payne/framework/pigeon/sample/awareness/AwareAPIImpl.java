package payne.framework.pigeon.sample.awareness;

import payne.framework.pigeon.core.Constants;
import payne.framework.pigeon.core.observation.Event;
import payne.framework.pigeon.core.observation.NotificationCenter;
import payne.framework.pigeon.core.observation.Observer;
import payne.framework.pigeon.server.InvocationContext;
import payne.framework.pigeon.server.InvocationContextAware;

/**
 * 通过实现{@link InvocationContextAware}接口在框架启动完成后做一些初始化的动作.
 * 这里顺便通过这个通知功能配合引入另一个功能:监听<br/>
 * 为了方便直接让这个类实现{@link Observer}接口,让这个类成为一个监听者.然后通过{@link NotificationCenter}
 * 注册监听感兴趣的事件;
 * 
 * @author yangchangpei
 *
 */
public class AwareAPIImpl implements AwareAPI, InvocationContextAware, Observer {

	/**
	 * 在框架启动完成后对它进行一些监听的绑定 框架提供的所有事件类型都已经配置在Constants常量类里面了 这里注册接受客户端连接的事件
	 * 这里invocationContext是一个事件源而this就是事件监听者
	 * ,notificationCenter只是两者之间的桥梁这样是为了将两者解耦,解除监听可以采用
	 * {@link NotificationCenter#detach(String, Observer)}方法
	 */
	public void setInvocationContext(InvocationContext invocationContext) throws Exception {
		invocationContext.getNotificationCenter().attach(Constants.CONNECTION_ACCEPT_EVENT_NAME, this);
	}

	/**
	 * 当感兴趣的事件发生时,框架会回调这个方法,事件的信息都在参数event里面
	 */
	public void react(NotificationCenter notificationCenter, Event event) {

	}

}

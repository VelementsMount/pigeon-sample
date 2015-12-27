package payne.framework.pigeon.sample.asynchronous;

import payne.framework.pigeon.client.AndroidClient;
import payne.framework.pigeon.client.AsyncClient;
import payne.framework.pigeon.core.Callback;
import payne.framework.pigeon.core.annotation.Open;
import payne.framework.pigeon.generation.annotation.Name;
import payne.framework.pigeon.generation.async.AsynchronousGeneratorService;

/**
 * 实际上在服务端看来没有同步异步之分,只有前台才有同步异步之分,我们定义接口的时候还是按照原先的接口来定义不必做任何调整!.
 * 在框架没有拓展异步接口生成的时候我们想在前台实现异步调用服务端的开放服务时,
 * 我们必须自己手写开启子线程去请求然后通过回调的方法执行请求完成后的代码,这种方式存在一些缺陷,因为这样需要写更多的代码而且请求和返回后的代码分开写,
 * 甚至在Android平台中这种写法更糟糕
 * ,因为Android平台中不能在主线程请求网络也不能在子线程中做UI的操作,最简单的办法也要为每个请求写一个AsyncTask,造成了不必要的类拓展.
 * 基于这个考虑,框架提供异步请求接口的生成,和objc接口生成一样框架提供了一个开放服务
 * {@link AsynchronousGeneratorService},只要注册进启动的开放接口容器中即可通过浏览器 输入路径来下载生成的异步接口.
 * 异步接口的方法和同步接口的方法有哪些异同呢?实际上框架在生成异步接口的时候对每个方法的请求参数全部保留而且在最后都添加了一个回调参数类型
 * {@link Callback}. 里面包含三个回调方法{@link Callback#onSuccess(Object)}
 * {@link Callback#onFail(Throwable)}和无论成功还是失败都会回调的
 * {@link Callback#onCompleted(boolean, Object, Throwable)} 而且会在最后执行.
 * 同时框架也要求异步的接口必须采用异步的客户端来创建,然后框架以异步的方式将实际请求参数来请求服务端并且将结果通过回调的方式回调最后一个参数(实际上这里就是
 * {@link Callback}的一个对象) 需要注意的是如果想实现Android平台的异步接口请求和回调需要用另外一个客户端来构建
 * {@link AndroidClient}采用这种方式构建的异步接口可以放心的在主线程进行调用开放方法
 * 而且在回调函数中进行UI操作,因为框架自动的在子线程进行网络交互然后通过主线程回调处理函数如
 * {@link Callback#onSuccess(Object)}...不像{@link AsyncClient}回调函数也是在子线程执行的
 * 
 * @author yangchangpei
 *
 * @see AsynchronousGeneratorService
 * @see Callback
 *
 */
@Open("/async")
public interface AsyncAPI {

	Customer login(@Name("username") String username, @Name("password") String password) throws Exception;

}

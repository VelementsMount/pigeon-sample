package payne.framework.pigeon.sample.generation;

import java.io.File;

import org.junit.Test;

import payne.framework.pigeon.generation.Generator;
import payne.framework.pigeon.generation.Interface;
import payne.framework.pigeon.generation.async.AsynchronousGenerator;
import payne.framework.pigeon.generation.objc.ObjectiveCGenerator;
import payne.framework.pigeon.generation.objc.ObjectiveCGeneratorService;
import payne.framework.pigeon.generation.reactive.ReactiveGenerator;
import payne.framework.pigeon.server.InvocationContext;
import payne.framework.pigeon.server.NonBlockingInvocationContext;

/**
 * 有两种方式可以生成开放接口的跨平台接口:<br/>
 * 1.通过代码,框架提供了一个{@link Generator}抽象类来抽象代码生成功能,现阶段只有一个实现 {@link ObjectiveCGenerator}<br/>
 * 2.通过浏览器下载,启动服务端的时候顺便把{@link ObjectiveCGeneratorService} 注册进去,然后通过浏览器或其他Http请求下载压缩包. <br/>
 * objective-c的框架客户端源码可以从github中下载 pigeon-objc 地址是:https://github.com/core-lib/pigeon-objc.git
 * 
 * @author yangchangpei
 *
 */
public class GenerationTests {

	/**
	 * 通过代码的方式生成跨平台API接口,具体使用方法请参考{@link Generator}和{@link ObjectiveCGenerator}
	 * 
	 * @see Generator
	 * @see ObjectiveCGenerator
	 * 
	 * @throws Exception
	 */
	@Test
	public void testObjcGenerator() throws Exception {
		String directory = System.getProperty("java.io.tmpdir") + "/WeiboAPI-objc";
		Generator generator = new ObjectiveCGenerator(directory);
		generator.generate(new Interface("/WeiboAPIImpl", WeiboAPI.class));
		System.out.println("objective-c WeiboAPI has been generated to directory:" + directory);
	}

	@Test
	public void testReactiveGenerator() throws Exception {
		File directory = new File(System.getProperty("java.io.tmpdir") + "/WeiboAPI-reactive");
		if (directory.exists() == false) {
			directory.mkdirs();
		}
		Generator generator = new ReactiveGenerator(directory);
		generator.generate(new Interface("/WeiboAPIImpl", WeiboAPI.class));
		System.out.println("objective-c WeiboAPI has been generated to directory : " + directory);
	}
	
	@Test
	public void testAsyncGenerator() throws Exception {
		File directory = new File(System.getProperty("java.io.tmpdir") + "/WeiboAPI-async");
		if (directory.exists() == false) {
			directory.mkdirs();
		}
		Generator generator = new AsynchronousGenerator(directory);
		generator.generate(new Interface("/WeiboAPIImpl", WeiboAPI.class));
		System.out.println("objective-c WeiboAPI has been generated to directory : " + directory);
	}

	/**
	 * 启动服务,注意看到下面这个服务端不只是注册了{@link WeiboAPIImpl}这个实现类,也注册了 {@link ObjectiveCGeneratorService}.通过访问这个服务就能下载想要的objc跨屏台开放接口,<br/>
	 * 试试在浏览器中敲入:http://localhost:9090/objc/generator/generate?implementation=/ WeiboAPIImpl<br/>
	 * 更多内容请参考{@link ObjectiveCGeneratorService}
	 * 
	 * @see ObjectiveCGeneratorService
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		InvocationContext context = new NonBlockingInvocationContext();
		context.bind(9090);
		context.register(new WeiboAPIImpl());
		context.register(new ObjectiveCGeneratorService(false));
		context.startup();
	}

}

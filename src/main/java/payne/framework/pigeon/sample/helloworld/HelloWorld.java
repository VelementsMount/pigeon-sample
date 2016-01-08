package payne.framework.pigeon.sample.helloworld;

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang.Validate;

import payne.framework.pigeon.core.annotation.Chunk;
import payne.framework.pigeon.core.annotation.Close;
import payne.framework.pigeon.core.annotation.Open;
import payne.framework.pigeon.core.annotation.Work;
import payne.framework.pigeon.core.compression.extend.Compress;
import payne.framework.pigeon.core.encoding.extend.Encode;
import payne.framework.pigeon.core.encryption.extend.Encrypt;
import payne.framework.pigeon.core.encryption.extend.Sign;

/**
 * 最简单的接口定义方式,只需要在接口类上面标注上{@link Open}注解即可,<br/>
 * 如果需要更多功能可以通过另外的注解来实现.<br/>
 * 接口方法上的参数及其返回值类型都是自定义的,数量也没有任何要求,框架不做要求,如果交互使用的数据格式是<br/>
 * 对象序列化(即application/x-java-serialized- object)格式<br/>
 * 则要求参数和返回值类型都必须实现序列化接口{@link Serializable}<br/>
 * 接口的定义可以完全参照java的接口定义即可,唯一一个要求就是接口必须声明{@link IOException}或者更宽的异常<br/>
 * 因此,采用框架可以轻松对以前的API开放框架进行替换和集成.只需要在原有的接口上标注上必需的注解即可.
 * 
 * @see {@link Encrypt} 数据加密
 * @see {@link Compress} 数据压缩
 * @see {@link Validate} 数据校验
 * @see {@link Sign} 数据签名
 * @see {@link Encode} 数据编码
 * @see {@link Work} 请求配置
 * 
 * @author Payne
 *
 */
@Open
@Chunk(size = 1024)
public interface HelloWorld {

	/**
	 * 方法上也可以通过相同的注解覆盖类上的注解,实现个性化功能,当然也可以默认使用类上的注解<br/>
	 * 即使方法在服务端运行时不会有抛出也不能保证客户端不抛出异常,因为网络传输中有很多不可抗拒的错误,如网络异常,断网,权限不足等等<br/>
	 * 而造成的异常,所以接口上必须定义抛出异常,让客户端必须处理是比较安全的方案. 方法上必须定义 {@link IOException} 或者范围更宽的异常,如{@link Exception}和{@link Throwable}
	 * 
	 * @param hi
	 * @return
	 * @throws Exception
	 */
	String hello(String hi) throws Exception;

	/**
	 * 因为默认情况下,注解{@link Open#all()} 属性是true,意味着接口上得所有方法都是开放且可远程调用的,如果想其中一些方法只允许在内部使用, 这时候注解{@link Close}<br/>
	 * 就有很好的作用了,任何情况下,标注了该注解的方法都不允许被外部访问,只能在程序内部使用<br/>
	 * 
	 * @param hi
	 * @return
	 */
	@Close
	boolean validate(String hi);

}

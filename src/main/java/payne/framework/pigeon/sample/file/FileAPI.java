package payne.framework.pigeon.sample.file;

import java.io.File;

import payne.framework.pigeon.core.Document;
import payne.framework.pigeon.core.annotation.Open;
import payne.framework.pigeon.core.compression.extend.Compress;

/**
 * 文件API,因为文件一般数据比较大,所以压缩可以提高网络传输效率,只需要通过注解{@link Compress}即可轻松拥有数据压缩功能
 * 
 * @author Payne
 *
 */
@Open
public interface FileAPI {

	/**
	 * 因为java中{@link File}并没有真正把文件内容序列化,所以框架提供了一个可序列化文件内容的类{@link Document}<br/>
	 * 把{@link Document}作为参数或参数类型里面的嵌套属性即可实现文件上传功能.<br/>
	 * 
	 * @param file
	 * @throws Exception
	 */
	void upload(Document file) throws Exception;

	/**
	 * 把{@link Document}作为返回值类型或返回值类型的嵌套属性即可实现文件下载功能<br/>
	 * 
	 * @return
	 * @throws Exception
	 */
	Document download() throws Exception;

}

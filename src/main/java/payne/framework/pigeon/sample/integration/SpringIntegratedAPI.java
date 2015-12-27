package payne.framework.pigeon.sample.integration;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import payne.framework.pigeon.core.annotation.Open;

/**
 * 在这里展示一个如何跟spring集成的例子,首先要在spring的配置文件中把框架启动起来,请参考项目中的<br/>
 * applicationContext. xml中关于框架配置的部分
 * 然后框架会在spring的Bean容器中自动寻找开放接口的实现类,所以这个时候的一个重要的配置就是要让开放接口的实现类标注上
 * {@link Service} ,{@link Component},{@link Controller}
 * 或者通过在spring的配置文件中将实现类这个bean交给spring来管理就行了,你也可以直接在其他例子中将实现类标注上注解
 * {@link Service} 然后启动项目,(项目集成了jetty插件,可以通过maven命令轻松启动)然后一样可以让Spring管理其他开放的接口服务
 * 
 * @author yangchangpei
 *
 */
@Open
public interface SpringIntegratedAPI {

	Long addProduct(Product product) throws Exception;

	Product getProduct(long productId) throws Exception;

}

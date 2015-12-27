package payne.framework.pigeon.sample.integration;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 标注上{@link Service}让spring管理这个bean,而且这个类实现了开放接口{@link SpringIntegratedAPI}
 * ,所以框架会从spring的bean容器中找到这个可开放接口的实现类并且将它开放给远程调用.这个时候就可以轻松使用spring提供的方便的功能了,
 * 像依赖注入,AOP,事务管理...<br/>
 * 因为框架与Spring集成,所以框架启动是要依赖Spring的,况且开放的接口实现类也是Spring管理所以如果要测试的话,
 * 直接把项目用tomcat或其他应用服务器跑起来再通过junit通过框架的客户端进行单元测试是没问题的
 * 但是这样一般会很耗时,因为项目打包然后启动tomcat这个过程很久. 所以通过junit就能启动项目并且单元测试就再好不过了,详情请参考测试类
 * {@link SpringIntegratedAPITests}<br/>
 * 
 * 
 * @author yangchangpei
 *
 */
@Service
public class SpringIntegratedAPIImpl implements SpringIntegratedAPI {

	// 因为是spring管理的bean所以可以轻松使用spring提供的依赖功能
	@Resource
	private ProductService productServiceBean;

	public Long addProduct(Product product) throws Exception {
		productServiceBean.save(product);
		return product.getId();
	}

	public Product getProduct(long productId) throws Exception {
		return productServiceBean.getProductById(productId);
	}

}

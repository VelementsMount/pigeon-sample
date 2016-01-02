package payne.framework.pigeon.sample.springmvc;

import java.util.List;

import payne.framework.pigeon.core.annotation.Open;

@Open("/product")
public interface ProductAPI {

	@Open("/list")
	List<Product> list(Pagination pagination) throws Exception;

}

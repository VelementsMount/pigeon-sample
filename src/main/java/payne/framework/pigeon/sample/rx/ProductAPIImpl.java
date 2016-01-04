package payne.framework.pigeon.sample.rx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Company: 广州市俏狐信息科技有限公司
 * </p>
 * 
 * @author yangchangpei 646742615@qq.com
 *
 * @date 2016年1月3日 下午6:05:59
 *
 * @version 1.0.0
 */
public class ProductAPIImpl implements ProductAPI {

	@Override
	public List<Product> list(int page, int size) throws Exception {
		page = page < 1 ? 1 : page;
		size = size < 1 ? 10 : size;

		List<Product> products = new ArrayList<Product>();
		for (int i = (page - 1) * size; i < page * size; i++) {
			products.add(new Product(Long.valueOf(i), "name" + i));
		}

		Collections.shuffle(products);

		return products;
	}

}

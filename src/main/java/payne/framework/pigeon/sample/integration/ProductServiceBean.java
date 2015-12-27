package payne.framework.pigeon.sample.integration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ProductServiceBean implements ProductService {
	private Map<Long, Product> database = new HashMap<Long, Product>();

	{
		for (int i = 0; i < 100; i++) {
			Product product = new Product();
			product.setId(Long.valueOf(i));
			product.setProductName("product-name-" + i);
			product.setPrice(i);
			database.put(product.getId(), product);
		}
	}

	public void save(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("product must not be null!");
		}
		Long id = (long) database.size();
		product.setId(id);
		database.put(product.getId(), product);
	}

	public Product getProductById(long id) {
		return database.get(id);
	}

}

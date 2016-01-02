package payne.framework.pigeon.sample.springmvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product/*")
public class ProductController {

	@ResponseBody
	@RequestMapping("/list")
	public List<Product> list(@RequestBody Pagination pagination) {
		List<Product> products = new ArrayList<Product>();
		for (int i = 0; i < pagination.getSize(); i++) {
			products.add(new Product("name" + i, i));
		}
		return products;
	}

}

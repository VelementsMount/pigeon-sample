package payne.framework.pigeon.sample.jaxb;

public class JaxbAPIImpl implements JaxbAPI {

	public Order getOrderById(long orderId, String fetchs) throws Exception {
		Customer current = new Customer();
		current.setId(1L);
		current.setNickname("Payne");
		current.setUsername("payne");
		current.setPassword("123456");

		Order order = new Order();
		order.setId(orderId);
		order.setCustomer(current);
		order.setNote("");

		float amount = 0.0f;
		for (int i = 0; i < 3; i++) {
			OrderItem item = new OrderItem();
			item.setId(Long.valueOf(i));
			Product product = new Product();
			product.setId(Long.valueOf(i));
			product.setPrice(10.0f);
			product.setProductName("pigeon");
			item.setProduct(product);
			item.setQuantity(i);
			order.getOrderItems().add(item);

			amount += product.getPrice() * item.getQuantity();
		}

		order.setAmount(amount);

		return order;
	}

}

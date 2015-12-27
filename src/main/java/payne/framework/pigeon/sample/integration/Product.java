package payne.framework.pigeon.sample.integration;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 533856923976902830L;

	private Long id;
	private String productName;
	private float price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}

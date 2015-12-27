package payne.framework.pigeon.sample.jaxb;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 标注上{@link XmlRootElement}表明该类型可以通过jaxb的xml格式序列化
 * 
 * @author Payne
 *
 */
@XmlRootElement
public class Product implements Serializable {
	private static final long serialVersionUID = -9075704216891440702L;

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

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", price=" + price + "]";
	}

}

package payne.framework.pigeon.sample.jaxb;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 标注上{@link XmlRootElement}表明该类型可以通过jaxb的xml格式序列化
 * 
 * @author Payne
 *
 */
@XmlRootElement
public class Order implements Serializable {
	private static final long serialVersionUID = 3523656022343730469L;

	private Long id;
	private Customer customer;
	private float amount;
	private String note;
	private Set<OrderItem> orderItems = new LinkedHashSet<OrderItem>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", amount=" + amount + ", note=" + note + ", orderItems=" + orderItems + "]";
	}

}

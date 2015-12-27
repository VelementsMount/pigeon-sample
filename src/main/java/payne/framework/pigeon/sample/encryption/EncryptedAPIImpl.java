package payne.framework.pigeon.sample.encryption;

import payne.framework.pigeon.sample.jaxb.Customer;

/**
 * 实现类不用做任何特殊处理
 * 
 * @author yangchangpei
 *
 */
public class EncryptedAPIImpl implements EncryptedAPI {

	public Customer login(String username, String password) throws Exception {
		return new Customer(1L, username, password, "pigeon");
	}

}

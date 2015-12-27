package payne.framework.pigeon.sample.asynchronous;  

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
 * @date 2015年8月27日 下午9:11:24
 *
 * @version 1.0.0 
 */
public class AsyncAPIImpl implements AsyncAPI{

	public Customer login(String username, String password) throws Exception {
		return new Customer(username, password);
	}

}
 
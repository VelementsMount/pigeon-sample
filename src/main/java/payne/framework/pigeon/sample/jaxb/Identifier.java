package payne.framework.pigeon.sample.jaxb;

import java.io.Serializable;

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
 * @date 2016年1月3日 下午4:32:58
 *
 * @version 1.0.0
 */
public class Identifier implements Serializable {
	private static final long serialVersionUID = 8553105571226288350L;

	private Long value;

	public Identifier() {
		super();
	}

	public Identifier(Long value) {
		super();
		this.value = value;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

}

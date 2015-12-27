package payne.framework.pigeon.sample.get;

import java.util.Map;

import payne.framework.pigeon.core.annotation.Open;

/**
 * GetableAPI开放接口的一个实现,在这里我没有通过{@link Open}
 * 注解来修改这个实现类的路径,所以默认也就采用了/GetableAPIImpl
 * 
 * @author yangchangpei
 *
 */
@Open
public class GetableAPIImpl implements GetableAPI {

	/**
	 * 实际上实现类上的方法的路径才是唯一确定的,像这个方法的路径显然就是/GetableAPIImpl/GetableAPI/login<br/>
	 * 注意,{@link Open}注解用在实现类的方法上没有任何效果.必须用在接口的方法上.
	 */
	public boolean login(String username, String password) throws Exception {
		return (username != null && !username.trim().equals("")) && (password != null && !password.trim().equals(""));
	}

	/**
	 * 显然这个方法的路径是:/GetableAPIImpl/GetableAPI/loginByMap
	 */
	public boolean login(Map<String, String> map) throws Exception {
		if (map == null || map.size() < 2) {
			return false;
		}
		String username = map.get("username");
		String password = map.get("password");
		return login(username, password);
	}

}

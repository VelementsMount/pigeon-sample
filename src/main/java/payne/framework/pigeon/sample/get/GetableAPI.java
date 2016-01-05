package payne.framework.pigeon.sample.get;

import java.util.Map;

import payne.framework.pigeon.core.annotation.Open;
import payne.framework.pigeon.core.annotation.Param;

/**
 * 因为框架默认就是采用HTTP作为传输协议客户端采用POST方法进行请求,所以所有开放的接口都可以通过HTTP来访问,
 * 无论是采用HttpURLConnection还是通过浏览器直接敲路径来访问都是可以的.
 * 不过要是采用自定义的http方式或浏览器访问必须清楚框架如何映射每个方法的路径.
 * 框架采用三级路径来映射每一个开放的方法,也可以认为框架通过类似maven的三个坐标
 * (groupId,artifactId,version)来标示每一个工程,<br/>
 * 在框架里面采用实现类/接口/方法三个坐标来唯一映射一个方法,你可以把框架容器看做一个立体坐标轴,x代表实现类,y代表接口,z代表方法,<br/>
 * 那么x,y, z就可以唯一确定一个实现类的方法<br/>
 * 如果你细心的话,你会发现注解{@link Open}有这个一个属性 {@link Open#value()}叫做value,<br/>
 * 而且该注解可以用于实现类,接口和方法上,通过value属性映射路径,属性是有默认值的,默认情况下<br/>
 * 1.如果实现类上没有标注{@link Open}注解或value属性默认,就直接采用实现类的简单名称
 * {@link Class#getSimpleName()}<br/>
 * 2.开放的接口必须标注{@link Open}注解,当时如果value值为默认的情况下也是采用{@link Class#getSimpleName()}
 * 作为路径<br/>
 * 3.方法上如果没有标注{@link Open}或value默认情况下采用方法的名称作为路径<br/>
 * 
 * 如果你发现采用默认的路径有重复的话,这种情况通常出现在多个重载的方法上即同一个接口上或同一个实现类实现了多个同名的开放方法,<br/>
 * 那么需要在某一个同名的开放方法上使用{@link Open#value()}来修改映射路径<br/>
 * 一般情况下不需要修改框架映射的路径.<br/>
 * 另外再提醒一点就是实际上所有开放的接口都可以通过普通的http请求访问,框架实现了http的post和get请求
 * 
 * @author yangchangpei
 *
 */
// 默认采用的路径映射方式为:/GetableAPI
@Open
public interface GetableAPI {

	/**
	 * 因为一个接口可以有多个实现类,所以实际上,通过接口来看是不能确定每个方法的路径的,必须要结合这个接口的实现类来看,
	 * 当时能确定的是该方法路径的后两级一定是 /Getable/login,这时候如果其中一个实现类的开放路径是/GetableAPIImpl
	 * 那么该实现类的这个方法声明的实现方法的开放路径就能唯一确定了,即
	 * /GetableAPIImpl/Getable/login,通过浏览器输入这个路径就可以访问了.但是问题也来了!<br/>
	 * 这个方法上需要两个参数,如果在路径中把两个参数也传过来?这个时候需要用到另外一个注解:{@link Param}<br/>
	 * 通过对方法的每个参数标注一个注解{@link Param}使用value属性指定参数的名称即可通过路径传过来.<br/>
	 * 如:<br/>
	 * http://localhost:9090/GetableAPIImpl/Getable/login?username=zhangsan&
	 * password=123456<br/>
	 * 在这里顺便说一下如果不写{@link Param}
	 * 框架会如何处理?其实不写这个注解也是可以的,只不过参数的名称会默认采用argument0,argument1...来代替<br/>
	 * 所以,如果参数上没有{@link Param} 注解时,路径应该写成:<br/>
	 * http://localhost:9090/GetableAPIImpl/Getable/login?argument0= zhangsan&
	 * argument1=123456<br/>
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return true:如果登陆成功 false:否则
	 * @throws Exception
	 */
	// 采用默认的路径映射方式:/login
	public boolean login(@Param("username") String username, @Param("password") String password) throws Exception;

	/**
	 * 在这里我们看到,因为接口上定义了两个同名的方法,这是路径是回映射到同一个路径的,这是不允许的,因为一个路径必须唯一映射一个方法, 所以我们可以采用
	 * {@link Open}的{@link Open#value()}
	 * 属性对路径进行修改.在这里我把这个本来是:/login的路径改成了/loginByMap,此做法用在接口和实现类上效果也是一样的.<br/>
	 * 所以如果也是在路径为/GetableAPIImpl的实现类里唯一标示这个方法的路径就是:<br/>
	 * /GetableAPIImpl/GetableAPI/ loginByMap<br/>
	 * 像这个方法如果想要通过浏览器方法就变成了:<br/>
	 * http://localhost:9090/GetableAPIImpl/Getable/
	 * loginByMap?argument0.username=zhangsan&argument0.password=123456<br/>
	 * 这里顺便说一下如果通过get路径的参数构造一个map.<br/>
	 * 因为我们看到参数map上没有标注{@link Param}所以它的名字就默认采用argument0,这里的0代表它是第0个参数.<br/>
	 * 如果想往map里放入一个entry={username:zhangsan}那么就可以写成argument0.username=zhangsan<br/>
	 * 同理,如果想往map里放入一个entry={password:123456}那么就可以写成argument0.password=123456<br/>
	 * 如果想同时放那么就用&拼接起来也就成了argument0.username=zhangsan&argument0.password=123456
	 * 
	 * @param map
	 *            包含用户名密码的一个map
	 * @return true:如果登陆成功 false:否则
	 * @throws Exception
	 */
	@Open("/loginByMap")
	public boolean login(Map<String, String> map) throws Exception;

}

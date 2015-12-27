package payne.framework.pigeon.sample.validation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import payne.framework.pigeon.core.annotation.Open;
import payne.framework.pigeon.core.validation.ValidationInterceptor;

/**
 * 在这里我展示一个对参数和返回值进行校验的例子,还是那注册登陆来做例子吧,至少跟真正项目有点关系. <br/>
 * 请参考Bean Validation 规范JSR-349,框架采用的BeanValidation-1.1.0
 * API,实际上有很多JSR-349规范的实现,<br/>
 * 像Hibernate validator 5,一定要5.0版本以上才支持哦.如何与框架继承?答案当然是通过拦截器.<br/>
 * 在实现类上加入{@link ValidationInterceptor}拦截器即可轻松获得开放方法的参数和返回值的校验功能.<br/>
 * 需要注意的是,框架只是依赖了Bean-Validation-API-1.1.0规范,没有相关的实现,所以如果要使用校验功能,你必须加入真正的某个厂商的实现<br/>
 * 如Hibernate-validator-5,详细说明请参考规范JSR-349:https://jcp.org/en/jsr/detail?id=349 <br/>
 * 通过参考{@link ValidationInterceptor}你只要实现拦截器,就能为框架拓展更多功能或者与其他框架进行集成<br/>
 * 
 * @author yangchangpei
 *
 */
@Open
public interface ValidatedAPI {

	/**
	 * 方法上的{@link Valid}代表需要对返回值类型{@link User}进行内部属性校验,<br/>
	 * 参数上的{@link Valid}代表需要对参数进行属性校验,{@link NotNull} 代表参数不能为空<br/>
	 * 注意!如果参数或返回值是复合类型,而且其中的字段或属性需要校验,而在这里没有加上{@link Valid}将不会校验其中的字段或属性
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	// 校验返回值
	@Valid
	User register(/* 如果需要校验复合对象里面的属性需要用@Valid */@Valid @NotNull User user) throws Exception;

	/**
	 * 你可以对实现类或测试类进行修改,看是否符合校验逻辑
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	// 校验返回值
	@Valid
	User login(@NotNull @Pattern(regexp = "\\w{6,12}") String username, @NotNull @Pattern(regexp = "\\w{6,12}") String password) throws Exception;

}

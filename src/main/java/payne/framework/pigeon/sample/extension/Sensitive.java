package payne.framework.pigeon.sample.extension;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import payne.framework.pigeon.core.annotation.Compress;
import payne.framework.pigeon.core.annotation.Encode;
import payne.framework.pigeon.core.annotation.Encrypt;
import payne.framework.pigeon.core.annotation.Process;
import payne.framework.pigeon.core.annotation.Sign;
import payne.framework.pigeon.core.annotation.Validate;
import payne.framework.pigeon.core.annotation.Work;
import payne.framework.pigeon.core.processing.Procedure;

/**
 * <p>
 * Description:敏感字符检查功能拓展,通过{@link Process}注解标明的处理工序类为
 * {@link InvocationSensitiveProcedure}
 * ,只要实现该类的处理逻辑,然后在开放接口或开放方法上加上这个注解即可轻松对框架进行拓展, 我们还看到{@link Process#step()}
 * 属性,这个属性是为了标明当开放方法上有多个数据处理工序注解时.
 * 这个注解的排序序号,越小即越先解析.所以这个时候我们在数据刚被序列化或即将被序列化的状态下检查.
 * 因为只有这个时候数据才真正是明文,或者说还没有被加密过,压缩和编码过
 * .这时候这个功能执行的顺序很重要必须在最开始执行.即数据刚被序列化成json或xml之类的字符格式,那么{@link Process#step()}
 * 应该是比其他的注解的要小,我们直接采用-1就行了,这就能保证执行的时候数据可以被检查.
 * </p>
 * 
 * <p>
 * 只要在注解上加上{@link Process}那么注解就和框架提供的{@link Encode},{@link Encrypt},
 * {@link Compress},{@link Sign},{@link Validate},{@link Work}
 * 没有什么区别,这样能对框架进行拓展,该注解就是直接对框架拓展一个敏感字符检查功能,注解的用法也是和框架提供的注解使用方法一样
 * ,只要在开放方法上或开放接口上标注该注解即可让框架在解析客户端请求和回应时在指定的步骤时调用指定的处理类({@link Procedure}
 * )的一些方法,所以这时候重点就放在如何实现 {@link InvocationSensitiveProcedure}上了
 * </p>
 * 
 * <p>
 * Company: 广州市俏狐信息科技有限公司
 * </p>
 * 
 * @author yangchangpei 646742615@qq.com
 *
 * @date 2015年7月25日 下午4:37:47
 * 
 * @version 1.0.0
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE, ElementType.METHOD })
@Documented
@Inherited
@Process(procedure = InvocationSensitiveProcedure.class, step = -1)
public @interface Sensitive {

	/**
	 * 默认值为 sensitives.txt
	 * 
	 * @return 敏感词语的文件的相对路径
	 */
	String value() default "sensitives.txt";

}

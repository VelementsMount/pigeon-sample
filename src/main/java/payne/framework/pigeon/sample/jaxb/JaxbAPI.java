package payne.framework.pigeon.sample.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

import payne.framework.pigeon.core.annotation.Open;

/**
 * 框架提供了很多数据格式的序列化和方序列化,像 <br/>
 * application/x-java-serialized-object <br/>
 * application/json <br/>
 * application/xml <br/>
 * application/smile <br/>
 * application/yaml <br/>
 * application/cbor <br/>
 * application/avro <br/>
 * application/x-www-form-urlencoded <br/>
 * application/hessian <br/>
 * application/hessian2 <br/>
 * application/burlap <br/>
 * application/url : 实际上就是url格式<br/>
 * jaxb实际上也只是一种数据格式,也就是application/xml类似json,但是为什么我要特意讲一下jaxb?<br/>
 * 因为框架要为自定义的对象进行序列化和反序列化的时候需要做些额外的配置:<br/>
 * 1.在项目中放入pigeon-jaxb.index文件然后标记可以序列化的自定义类全名<br/>
 * 2.自定义类如果想要拥有可序列化功能需要按照jaxb规范对类和属性进行注解标记,像类上必须有注解{@link XmlRootElement}<br/>
 * 这个例子中我们看到,我们虽然看起来只有一个对象类型{@link Order}参与序列化,但是为什么pigeon-jaxb.index中需要加入四个类型<br/>
 * 因为{@link OrderItem},{@link Product}和{@link Customer}实际上是{@link Order} 的属性,而且参与序列化,所以还要加上这两个类.<br/>
 * 框架除了要求需要第一个要求之外其他可以参照sun的jaxb规范来配置自定义类型.<br/>
 * 
 * @author Payne
 *
 */
@Open
public interface JaxbAPI {

	Order getOrderById(Identifier identifier) throws Exception;

}

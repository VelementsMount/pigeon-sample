package payne.framework.pigeon.sample.encryption;

import payne.framework.pigeon.core.annotation.Compress;
import payne.framework.pigeon.core.annotation.Encode;
import payne.framework.pigeon.core.annotation.Encrypt;
import payne.framework.pigeon.core.annotation.Open;
import payne.framework.pigeon.core.annotation.Sign;
import payne.framework.pigeon.core.annotation.Validate;
import payne.framework.pigeon.core.encryption.RSAInvocationEncryptor;
import payne.framework.pigeon.sample.jaxb.Customer;

/**
 * 框架现阶段提供了对对象序列化后数据进行5种转换,1.加密/解密,2.压缩/解压,3.摘要/校验,4.签名/验签,5编码/解码.
 * 每个转换都是可以配置也是可插拔式的配置.实际上是分别通过5个注解来配置的具体注解如下:{@link Encrypt},{@link Compress},
 * {@link Validate},{@link Sign},{@link Encode} 框架默认为每种转换实现了几个算法<br/>
 * 加密算法 DES, AES, DESede, RSA<br/>
 * 压缩算法 GZIP, deflate, LZ4, snappy<br/>
 * 摘要算法 MD5 , MD2, SHA-1, SHA-256<br/>
 * 签名算法 MD5WithRSA, MD2WithRSA, SHA1WithRSA, SHA256WithRSA<br/>
 * 编码算法 base64, base32, hex(十六进制编码)<br/>
 * 所有的转换都是可以同时作用在一个接口或方法上的,即可以既对数据加密同时也可以对数据压缩也可以签名等,具体看业务需求.
 * 只需要在接口或方法上标注上对应的注解指定相应的算法即可.在这个例子我就拿加密来做个例子.<br/>
 * 框架按照OpenSSL的原理实现加密功能,即在项目部署之前我们应该生成我们的非对称密钥对,既然是非对称的加密,那就要涉及到签名与验签.
 * 所以我们应该对开放接口或方法加上两个注解{@link Encrypt}和{@link Sign}
 * ,但是这样还不够,因为既然是非对称加密那么密钥从哪里来,当然是让开发人员事先准备好.
 * 这里我们就先用RSA非对称加密算法来说吧,我们可以简单的通过框架提供的功能
 * {@link RSAInvocationEncryptor#generate(int)}
 * 来生成我们需要的长度的密钥(通常1024位就行了看项目需求,位数越大破解难度越大,但是解密速度越慢.),生成好的密钥怎么告诉框架?
 * 答案是通过配置文件,我们可以在服务端项目中放入一个配置文件叫做rsa-private.properties,<br/>
 * 客户端项目中放入一个配置文件叫rsa-public.properties 详细配置要求请看项目中的这两个文件,<br/>
 * 把密钥和相关信息放在里面.这里必须注意的是,私钥必须放在服务端,而且打死也不要让别人知道. 然后对应公钥可以公开,一般就写死在客户端的配置文件中就行了.<br/>
 * 实际上签名/验签也是需要非对称密钥的,所以刚好一起用这份配置文件就行了.<br/>
 * 服务端和客户端在创建的时候就要做一些配置,具体请看{@link EncryptedAPITests}
 * 
 * @see EncryptedAPITests
 * @see Encrypt
 * @see Sign
 * 
 * @author yangchangpei
 *
 */
@Open
public interface EncryptedAPI {

	/**
	 * 你可能会很奇怪上面不是说要用非对称加密算法RSA作为加密算法吗,怎么接口上配置的是DES对称加密算法?<br/>
	 * 实际上这也是OpenSSL的原理,通过结合非对称加密和对称加密的各自优点实现数据加密功能.<br/>
	 * 在运行期,非对称密钥是不会变化的,而且通过公钥也是极其难可以说是不能计算出私钥的.
	 * 而每一次请求客户端都要为这次请求生成一个DES算法的56位对称密钥对数据加密
	 * ,然后用配置好的rsa公钥加密把这个DES对称密钥加密和数据一起发送给服务端,
	 * 服务端用配置好的对应rsa私钥将客户端的数据中对称密钥部分解密得到对称密钥,最后用对称密钥解密密文数据得到明文.
	 * 服务端回应数据的时候直接用客户端生成的这次密钥加密即可.<br/>
	 * 签名只在于服务端,因为服务端才有私钥才能签名而客户端有公钥所以就能验签这份数据是不是来自真正的服务端而不是冒充或钓鱼的.
	 * 
	 */
	@Encrypt(algorithm = "DES", keysize = 56)
	@Sign(value = "SHA1WithRSA")
	Customer login(String username, String password) throws Exception;

}

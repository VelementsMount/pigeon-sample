package payne.framework.pigeon.sample.httpclient;

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
 * @date 2016年1月5日 上午10:33:00
 *
 * @version 1.0.0
 */
public class Article implements Serializable {
	private static final long serialVersionUID = -1184987764855725101L;

	private Long id;
	private String author;
	private String name;
	private String content;

	public Article() {
		super();
	}

	public Article(Long id, String author, String name, String content) {
		super();
		this.id = id;
		this.author = author;
		this.name = name;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", author=" + author + ", name=" + name + ", content=" + content + "]";
	}

}

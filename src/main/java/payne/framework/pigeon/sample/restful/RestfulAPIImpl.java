package payne.framework.pigeon.sample.restful;

import java.util.ArrayList;
import java.util.List;

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
 * @date 2016年1月5日 下午2:46:07
 *
 * @version 1.0.0
 */
public class RestfulAPIImpl implements RestfulAPI {

	@Override
	public boolean update(Article article) throws Exception {
		return article != null;
	}

	@Override
	public Article find(String name) throws Exception {
		return new Article(null, name, null);
	}

	@Override
	public List<Article> list(int page, int size) throws Exception {
		page = page < 1 ? 1 : page;
		size = size < 1 ? 10 : size;

		List<Article> articles = new ArrayList<Article>();
		for (int i = (page - 1) * size; i < page * size; i++) {
			articles.add(new Article("author" + i, "name" + i, "content" + i));
		}
		return articles;
	}

}

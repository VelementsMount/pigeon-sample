package payne.framework.pigeon.sample.httpclient;

import java.util.List;

import payne.framework.pigeon.core.annotation.Accept;
import payne.framework.pigeon.core.annotation.Accept.Mode;
import payne.framework.pigeon.core.annotation.Open;
import payne.framework.pigeon.core.annotation.Param;

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
 * @date 2016年1月5日 上午10:32:49
 *
 * @version 1.0.0
 */
@Open
public interface ForumAPI {

	@Accept(modes = { Mode.GET })
	@Open("/articles/page={1:\\d+}/size={2:\\d+}")
	List<Article> listArticles(int page, int size) throws Exception;

	@Accept(modes = { Mode.GET })
	@Open("/article/{id:\\d+}")
	Article findArticle(@Param("id") Long id) throws Exception;

	@Accept(modes = { Mode.DELETE })
	@Open("/article/{id:\\d+}")
	boolean deleteArticle(@Param("id") Long id) throws Exception;

	@Accept(modes = { Mode.POST })
	@Open("/article")
	Article saveArticle(Article article) throws Exception;

	@Accept(modes = { Mode.PUT, Mode.PATCH })
	@Open("/article")
	boolean updateArticle(Article article) throws Exception;

}

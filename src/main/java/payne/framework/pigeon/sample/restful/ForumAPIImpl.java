package payne.framework.pigeon.sample.restful;

import java.util.ArrayList;
import java.util.List;

public class ForumAPIImpl implements ForumAPI {

	@Override
	public List<Article> listArticles(int page, int size) throws Exception {
		System.out.println("list articles page = " + page + " size = " + size);
		page = page < 1 ? 1 : page;
		size = size < 1 ? 10 : size;
		List<Article> articles = new ArrayList<Article>();
		for (int i = (page - 1) * size; i < page * size; i++) {
			articles.add(new Article((long) i, null, null, null));
		}
		return articles;
	}

	@Override
	public Article findArticle(Long id) throws Exception {
		System.out.println("find article id = " + id);
		return new Article((long) id, null, null, null);
	}

	@Override
	public boolean deleteArticle(Long id) throws Exception {
		System.out.println("delete article id = " + id);
		return id != null;
	}

	@Override
	public Article saveArticle(Article article) throws Exception {
		System.out.println("save article = " + article);
		article.setId(1L);
		return article;
	}

	@Override
	public boolean updateArticle(Article article) throws Exception {
		System.out.println("update article = " + article);
		return article != null;
	}

}

package payne.framework.pigeon.sample.restful;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface RetrofitForumAPI {

	@GET("/ForumAPIImpl/ForumAPI/articles/page={page}/size={size}")
	List<Article> listArticles(@Path("page") int page, @Path("size") int size);

	@GET("/ForumAPIImpl/ForumAPI/article/{id}")
	Article findArticle(@Path("id") Long id) throws Exception;

	@DELETE("/ForumAPIImpl/ForumAPI/article/{id}")
	boolean deleteArticle(@Path("id") Long id) throws Exception;

	@POST("/ForumAPIImpl/ForumAPI/article")
	Article saveArticle(@Body Article article) throws Exception;

	@PUT("/ForumAPIImpl/ForumAPI/article")
	boolean updateArticle(@Body Article article) throws Exception;
}

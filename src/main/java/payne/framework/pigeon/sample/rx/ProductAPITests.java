package payne.framework.pigeon.sample.rx;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import payne.framework.pigeon.server.InvocationContext;
import payne.framework.pigeon.server.bio.BlockingInvocationContext;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import rx.Subscriber;
import rx.functions.Func1;

import com.google.gson.Gson;

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
 * @date 2016年1月3日 下午6:09:00
 *
 * @version 1.0.0
 */
public class ProductAPITests {
	private InvocationContext context;
	private ProductRetrofitAPI api = new RestAdapter.Builder().setConverter(new GsonConverter(new Gson())).setEndpoint("http://127.0.0.1:9090").build().create(ProductRetrofitAPI.class);

	public static void main(String[] args) throws Exception {
		InvocationContext context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new ProductAPIImpl());
		context.startup();
	}

	@Before
	public void initialize() throws Exception {
		context = new BlockingInvocationContext();
		context.bind(9090);
		context.register(new ProductAPIImpl());
		context.startup();
	}

	@After
	public void destroy() throws Exception {
		context.shutdown();
	}

	@Test
	public void testListSynchronously() throws Exception {
		System.out.println(api.listSynchronously(1, 20));
	}

	@Test
	public void testListAsynchronously() throws Exception {
		api.listAsynchronously(2, 20, new Callback<List<Product>>() {

			@Override
			public void failure(RetrofitError error) {
				resume();
			}

			@Override
			public void success(List<Product> products, Response response) {
				System.out.println(products);
				resume();
			}

		});
		pause();
	}

	@Test
	public void testListObservablely() throws Exception {
		api.listObservablely(1, 10).map(new Func1<List<Product>, List<Product>>() {

			@Override
			public List<Product> call(List<Product> t) {
				Collections.sort(t, new Comparator<Product>() {

					@Override
					public int compare(Product a, Product b) {
						return a.getId().compareTo(b.getId());
					}
					
				});
				return t;
			}

		}).subscribe(new Subscriber<List<Product>>() {

			@Override
			public void onCompleted() {
				resume();
			}

			@Override
			public void onError(Throwable t) {

			}

			@Override
			public void onNext(List<Product> products) {
				System.out.println(products);
			}
		});

		pause();
	}

	private synchronized void pause() throws Exception {
		this.wait();
	}

	private synchronized void resume() {
		this.notifyAll();
	}

}

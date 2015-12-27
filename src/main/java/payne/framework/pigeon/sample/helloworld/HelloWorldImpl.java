package payne.framework.pigeon.sample.helloworld;

import payne.framework.pigeon.core.annotation.Intercept;
import payne.framework.pigeon.server.session.SessionInterceptor;

@Intercept({ SessionInterceptor.class })
public class HelloWorldImpl implements HelloWorld {

	public String hello(String hi) throws Exception {
		if (!validate(hi)) {
			throw new IllegalArgumentException("argument hi must not be null or empty string");
		}
		System.out.println("Client Say : " + hi);
		return "Hello Client I am Server!";
	}

	public boolean validate(String hi) {
		return hi != null && !hi.trim().equals("");
	}

}

package payne.framework.pigeon.sample.restful;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * @date 2016年1月5日 上午9:57:22
 *
 * @version 1.0.0
 */
public class Tests {

	public static void main(String[] args) throws Exception {
		String path = "/SampleAPIImpl/SampleAPI/page/{page:\\d+}/size/id-{size:\\d+}";
		Pattern pattern = Pattern.compile("\\{(?:(\\w+)\\:)?(.*?)\\}");
		Matcher matcher = pattern.matcher(path);
		while (matcher.find()) {
			for (int i = 0; i <= matcher.groupCount(); i++) {
				System.out.println(i + ":" + matcher.group(i));
			}
		}
	}

}

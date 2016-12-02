
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.chenxushao.gson.GsonUtils;
import com.chenxushao.gson.Person;

public class GsonUtilsTest {

	@Test
	public void object2Json1() {
		Person p = new Person();
		p.setBirth(new Date());
		p.setId("001");
		p.setName("陈绪绍");
		p.setPswd("admin");
		p.setSex(true);
		p.setAge(30);
		String jsonString = GsonUtils.object2Json(p);
		System.out.println(jsonString);
	}

	@Test
	public void object2Json2() {
		List<Person> list = new ArrayList<Person>();
		for (int i = 0; i < 3; i++) {
			Person p = new Person();
			p.setBirth(new Date());
			p.setId("001");
			p.setName("陈绪绍" + i);
			p.setPswd("admin");
			p.setSex(true);
			p.setAge(30);
			list.add(p);
		}
		String jsonString = GsonUtils.object2JsonDateSerializer(list,
				"yyyy-MM-dd HH:mm:ss");
		System.out.println(jsonString);
	}

	@Test
	public void test() throws IOException {
		// String content = FileUtils.readTextFile(new File(
		// "F:/WS_ILMP_WMS/Gson/test1.json"));
		//
		// JsonElement elt = new JsonParser().parse(content);
		// JsonObject jsonObj = elt.getAsJsonObject();
		// JsonElement jsonElt = jsonObj.get("datas");
	}
}

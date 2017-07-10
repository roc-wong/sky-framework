package org.sky.framework.test.gson.demo2;

import java.lang.reflect.Field;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 修改字段名称
 * 
 * @Description TODO
 * @author <a href="193832433@qq.com">sky</a>
 * @date 2016年2月23日 下午3:22:33
 */
public class FieldNamingStrategyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		User user = new User("1", "sky", "123456");

		System.out.println(gson.toJson(user));

		String json = "{id:\"1\" , name:\"syx\", \"password\":\"syx\"}";

		User user1 = gson.fromJson(json, User.class);
		
		System.out.println(user1);
	}

	private static Gson gson = new GsonBuilder().setFieldNamingStrategy(new FieldNamingStrategy() {

		public String translateName(Field f) {
			if ("password".equals(f.getName())) {
				return "modifyField";
			}
			return f.getName();
		}

	}).create();
}

package org.sky.framework.test.gson.demo2;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 排除特定字段
 * 
 * 比如 ：User的json数据为{id:”1” , name:”syxchina”,
 * password:”syx”},我们解析的时候是不想把password解析，我们想得到格式为：{id:”1” , name:”syxchina”}。
 * 
 * 第一种方法是使用Gson自带的注解，但任何时候取数据都没的那个字段。
 * 
 * 第二种方法使用GsonBuilder，设置ExclusionStrategy参数。
 * 
 * 
 * 
 * @Description TODO
 * @author <a href="193832433@qq.com">sky</a>
 * @date 2016年2月23日 下午3:19:23
 */

public class ExclusionStrategyTest {

	// 原串：{id:"1" , name:"syx", password:"syx"}
	// 效果：{id:"1" , name:"syx"}

	private static Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
		public boolean shouldSkipField(FieldAttributes f) {
			if ("password".equals(f.getName())) {
				return true;
			}
			return false;
		}

		public boolean shouldSkipClass(Class<?> clazz) {
			return false;
		}
	}).create();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 对象可以
		User user = new User("1", "sky", "123456");
		String userJson = gson.toJson(user);

		System.out.println(userJson);
		System.out.println(gson.fromJson(userJson, User.class));

		// map 不行
		String json = "{id:\"1\" , name:\"syx\", \"password\":\"syx\"}";
		Type type = new TypeToken<Map<String, Object>>() {
		}.getType();
		Map<String, Object> map = gson.fromJson(json, type);
		System.out.println(map);
		System.out.println(gson.toJson(map));

	}
}

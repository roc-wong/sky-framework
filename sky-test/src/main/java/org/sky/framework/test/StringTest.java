package org.sky.framework.test;

import java.text.MessageFormat;

public class StringTest {
	
	public static void main(String[] args) {
		String ret = MessageFormat.format("赞！{0}{1}又涨了！", "sunshine", "asdfsafsafsd");
		
		System.out.println(ret);
	}
}

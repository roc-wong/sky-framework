package org.sky.framework.test.innerclass;

/**
 * 
 * @Description 抽象类 和 接口拥有同样的方法
 * @author <a href="193832433@qq.com">sky</a>   
 * @date 2015年6月2日 下午2:53:39
 */
public abstract class MyIncrement {

	public void increment() {
		System.out.println("MyIncrement increment()");
	}

	public void f(MyIncrement f) {
		f.increment();
	}

}
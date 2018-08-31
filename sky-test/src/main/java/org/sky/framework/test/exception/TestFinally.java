package org.sky.framework.test.exception;

/**
 * Description:
 *
 * 任何执行try 或者catch中的return语句之前，都会先执行finally语句，如果finally存在的话。
 * 如果finally中有return语句，那么程序就return了，所以finally中的return是一定会被return的，
 * 编译器把finally中的return实现为一个warning。
 * Created by roc on 4/28/2017.
 */
public class TestFinally {


	public static void main(String[] args) {
		System.out.println(TestFinally.test0());
        System.out.println("------------------");
        System.out.println(TestFinally.test1());
        System.out.println("------------------");
        System.out.println(TestFinally.test2());
        System.out.println("------------------");
        System.out.println(TestFinally.test3());
        System.out.println("------------------");
		System.out.println(TestFinally.test4());
	}

	public static int test0() {
		int x = 1;
		try {
			System.out.println("x = " + x);
			System.out.print("test0->");
			int tmp = 1 / 0;
			return x;
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			x = 2;
		}
		return x=3;
	}

	/**
	 *  程序执行try块中return之前（包括return语句中的表达式运算）代码，再执行finally块，最后执行try中return;
	 *  finally块之后的语句return，因为程序在try中已经return所以不再执行。
	 * @return
	 */
	public static int test1() {
		int x = 1;
		try {
			System.out.println("x = " + x);
			System.out.print("test1->");
			return x;
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			x = 2;
		}
		return x=3;
	}

	/**
	 *  程序先执行try，如果遇到异常执行catch块，
	 *  有异常：则执行catch中return之前（包括return语句中的表达式运算）代码，再执行finally语句中全部代码，
	 *      最后执行catch块中return. finally之后也就是4处的代码不再执行。
	 *  无异常：执行完try再finally再return.
	 * @return
	 */
	public static int test2() {
		int x = 1;
		try {
			System.out.println("x = " + x);
			System.out.print("test2->");
			int tmp = 1 / 0;
		} catch (Exception e){
			e.printStackTrace();
			return x + 99;
		}finally {
			x = 3; //即使改变了x的值，也无法改变函数返回值栈顶的值
		}
		return 4;//不再执行
	}


	/**
	 *     程序执行try块中return之前（包括return语句中的表达式运算）代码；
	 *     再执行finally块，因为finally块中有return所以提前退出。
	 * @return
	 */
	public static int test3() {
		int x = 1;
		try {
			System.out.println("x = " + x);
			System.out.print("test3->");
			return x + 99;
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			x = 3;
			return x;//提前return，不再执行try中的return
		}
	}

	/**
	 * 程序执行try块中return之前（包括return语句中的表达式运算）代码；
	 *   有异常：执行catch块中return之前（包括return语句中的表达式运算）代码；
	 *      则再执行finally块，因为finally块中有return所以提前退出。
	 *  无异常：则再执行finally块，因为finally块中有return所以提前退出。
	 * @return
	 */
	public static int test4() {
		int x = 1;
		try {
			System.out.println("x = " + x);
			int tmp = 1 / 0;
			System.out.print("test4->");
			return x + 99;
		} catch (Exception e){
			e.printStackTrace();
			x = 2;
			return x;
		}finally {
			x = 3;
			return x;
		}
	}

}

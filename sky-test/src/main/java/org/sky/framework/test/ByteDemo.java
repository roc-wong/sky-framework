package org.sky.framework.test;

/**
 * Java中的位,字节,字母,汉字之间的关系
 * 
 * @see =========================
 * @see 1 bit = 1 二进制数据
 * @see 1 byte = 8 bit (范围:-128到+127)
 * @see 1 字母 = 1 byte = 8 bit
 * @see 1 汉字 = 2 byte = 16 bit
 * @see ===========================
 * @author 玄玉<http://blog.csdn/net/jadyer>
 * @create Aug 20, 2012 10:28:20 AM
 */
public class ByteDemo {
	/**
	 * @see 控制台打印结果,如下
	 * @see 4
	 * @see 4
	 * @see ----------------------------
	 * @see str=ABab
	 * @see 65
	 * @see 66
	 * @see 97
	 * @see 98
	 * @see ----------------------------
	 * @see name=玄玉
	 * @see -48
	 * @see -2
	 * @see -45
	 * @see -15
	 * @see ----------------------------
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// byte bb11 = 128; //cannot convert from int to byte
		// byte bb22 = -129; //cannot convert from int to byte
		byte bb11 = 127;
		byte bb22 = -128;
		byte bb33 = 56;

		// byte bb44 = '玄'; //错误,因为: 1汉字==2字节
		// byte bb55 = 'bb'; //错误,因为: 'bb'==2字节
		byte bb44 = 'b';
		byte bb55 = 'B';
		byte bb66 = 'Z';

		// int i11 = "玄玉"; //1 int = 32 bit, but int is numeral
		// short ss = '玄玉'; //1 short = 2 byte = 16 bit = 1 汉字
		short ss11 = '玄';

		// char cc11 = '玄玉'; //这样是不可以的
		// char cc22 = 'cc'; //这样是不可以的
		char cc11 = '玄';
		char cc22 = 'c';

		String str = "ABab";
		byte[] data = str.getBytes();
		System.out.println(str.length());
		System.out.println(data.length);
		System.out.println("----------------------------");

		System.out.println("str=" + new String(data));
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
		System.out.println("----------------------------");

		byte[] name = "玄玉".getBytes();
		System.out.println("name=" + new String(name));
		for (int i = 0; i < name.length; i++) {
			System.out.println(name[i]);
		}
		System.out.println("----------------------------");
	}
}
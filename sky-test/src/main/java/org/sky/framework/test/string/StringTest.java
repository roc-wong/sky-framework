package org.sky.framework.test.string;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Description:
 * Created by roc on 6/12/2017.
 */
public class StringTest {
	public static void main(String[] args) {
		String[] parameters = null;

		System.out.println(StringUtils.join(parameters, ","));

		System.out.println(NumberUtils.isNumber("3.93"));
	}
}

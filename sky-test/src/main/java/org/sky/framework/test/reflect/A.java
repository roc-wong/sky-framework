package org.sky.framework.test.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

class A<T> {

	public Class getTClass(int index) {
		Type genType = getClass().getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			throw new RuntimeException("Index out of bounds");
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}
}

package org.sky.framework.test.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author roc
 * @date 2018/08/01
 */
public class Invoker {

    public <T> void invoke(SubscribeCallback<T> callback){
        Type[] genericSuperclass = callback.getClass().getGenericInterfaces();

        Type type = ((ParameterizedType) genericSuperclass[0]).getActualTypeArguments()[0];
        try {
            Class clazz = (Class) type;
            Object instance = clazz.newInstance();
            System.out.println(instance);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}

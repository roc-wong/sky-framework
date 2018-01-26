package org.sky.framework.test.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * @author roc
 * @date 2017/12/26
 */
public class TestServiceBootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceBootstrap.class);

    public static void main(String[] args) {

        Annotation[] annotations = Test1Service.class.getAnnotations();
        LOGGER.info("Test1Service.class.getAnnotations() = {}", Arrays.toString(annotations));

        Annotation[] annotations1 = Test1ServiceImpl.class.getAnnotations();
        Annotation[] annotations11 = Test1ServiceImpl.class.getDeclaredAnnotations();
        LOGGER.info("Test1ServiceImpl.class.getAnnotations() = {}", Arrays.toString(annotations1));
        LOGGER.info("Test1ServiceImpl.class.getAnnotations() = {}", Arrays.toString(annotations11));

        Annotation[] annotations2 = Test2ServiceImpl.class.getAnnotations();
        Annotation[] annotations3 = Test2ServiceImpl.class.getDeclaredAnnotations();
        LOGGER.info("Test2ServiceImpl.class.getAnnotations() = {}", Arrays.toString(annotations2));
        LOGGER.info("Test2ServiceImpl.class.getDeclaredAnnotations() = {}", Arrays.toString(annotations3));


        Test1ServiceImpl test1Service = new  Test1ServiceImpl();
        for (Class<?> clazz = test1Service.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            Annotation[] annotations111 = clazz.getDeclaredAnnotations();
            LOGGER.info("Test1ServiceImpl.class() = {}, {}", clazz.getSimpleName(), Arrays.toString(annotations111));

        }
    }

}

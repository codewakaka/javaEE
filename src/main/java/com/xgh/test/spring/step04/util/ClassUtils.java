package com.xgh.test.spring.step04.util;

/**
 * com.xgh.test.spring.step04.util.ClassUtils
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月27日
 */
public class ClassUtils {

    public static  ClassLoader getDefaultClassLoader(){
        ClassLoader cl = null;
        try{
            cl = Thread.currentThread().getContextClassLoader();
        }catch (Throwable ex){
            System.out.println(ex);
        }
        if(cl == null){
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }
}

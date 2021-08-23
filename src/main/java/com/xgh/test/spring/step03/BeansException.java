package com.xgh.test.spring.step03;

/**
 * com.xgh.test.spring.step02.BeansException
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月17日
 */
public class BeansException extends RuntimeException{

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}

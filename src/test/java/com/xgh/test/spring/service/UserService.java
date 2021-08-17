package com.xgh.test.spring.service;

/**
 * com.xgh.test.spring.service.UserService
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月12日
 */
public class UserService {
    private String name;

    public UserService(String name) {
        this.name = name;
    }

    public UserService() {
    }

    public void getUserInfo(){
        System.out.println("获取用户信息"+name);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("=====").append(name);
        return sb.toString();
    }


}

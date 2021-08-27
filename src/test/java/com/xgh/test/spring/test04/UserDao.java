package com.xgh.test.spring.test04;

import java.util.HashMap;
import java.util.Map;

/**
 * com.xgh.test.spring.test03.UserDao
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月24日
 */
public class UserDao {


    private static  Map<String ,String> hashMap = new HashMap<>();


    static {
        hashMap.put("10001","张三");
        hashMap.put("10002","李四");
        hashMap.put("10003","王五");
    }


    public String  queryUserName(String uId){
        return  hashMap.get(uId);
    }
}

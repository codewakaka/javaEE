package com.xgh.test.spring.test03;

/**
 * com.xgh.test.spring.test03.UserService
 *
 * @author xgh <br/>
 * @description
 * @date 2021年08月24日
 */
public class UserService {

    private String uId;

    private UserDao userDao;

    public UserService() {
    }

    public void  queryUserInfo(){
        System.out.println("查询用户信息：" + userDao.queryUserName(uId));
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}

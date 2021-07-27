package com.xgh.test.design;

/**
 * com.xgh.test.design.WalletRpcServiceOne
 *
 * @author xgh <br/>
 * @description
 * @date 2021年07月08日
 */
public class WalletRpcServiceOne implements WalletRpcService{
    @Override
    public String moveMoney(String id, Long fromUserId, Long toUserId, Double amount) {
        return "213112abc";
    }
}

package com.xgh.test.design;

/**
 * com.xgh.test.design.WalletRpcService
 *
 * @author xgh <br/>
 * @description
 * @date 2021年07月08日
 */
public interface WalletRpcService {

    String moveMoney(String id, Long fromUserId, Long toUserId, Double amount);
}

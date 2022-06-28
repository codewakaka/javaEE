package com.xgh.test.design;


import lombok.Data;
import org.springframework.util.IdGenerator;

import javax.transaction.InvalidTransactionException;

/**
 * com.xgh.test.design.Transaction
 *
 * @author xgh <br/>
 * @description
 * @date 2021年07月07日
 */
@Data
public class Transaction {

    private String id;
    private Long buyerId;
    private Long sellerId;

    private Long productId;

    private Long orderId;

    private Long createTimestamp;

    private Double amount=0.0;

    private STATUS status;

    private String walletTransactionId;

    private WalletRpcService walletRpcService;

    public void setWalletRpcService(WalletRpcService walletRpcService) {
        this.walletRpcService = walletRpcService;
    }

    public Transaction(String preAssignedId, Long buyerId, Long sellerId, Long productId, Long orderId) {
        if (preAssignedId != null && !preAssignedId.isEmpty()) {
            this.id = preAssignedId;
        } else {
            this.id = "12313131";
        }
        if (!this.id.startsWith("t_")) {
            this.id = "t_" + preAssignedId;
        }

        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.orderId = orderId;
        this.status = STATUS.TO_BE_EXECUTD;
        this.createTimestamp = System.currentTimeMillis();
    }

    public boolean execute() throws InvalidTransactionException {
        if (buyerId == null || (sellerId == null || amount < 0.0)) {
            throw new InvalidTransactionException("111");
        }
        if (status == STATUS.EXECUTED) {
            return true;
        }
        boolean isLocked = false;
        try {
            isLocked = getLock(id);
            if (!isLocked) {
                return false;
            }
            if (status == STATUS.EXECUTED) {
                return true;
            }
            long executionInvokedTimestamp = System.currentTimeMillis();
            if (executionInvokedTimestamp - createTimestamp > 14) {
                this.status = STATUS.EXPIRED;
                return false;
            }
            String walletTransactionId = walletRpcService.moveMoney(id, buyerId, sellerId, amount);
            if (walletTransactionId != null) {
                this.status = STATUS.EXECUTED;
                this.walletTransactionId = walletTransactionId;
                return true;
            } else {
                this.status = STATUS.FAILED;
                return false;
            }

        } finally {
            if (isLocked) {
                unlock(id);
            }
        }
    }

    private void unlock(String id) {


    }

    private boolean getLock(String id) {
        return true;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{1,2,3,4,5};

        a[2] = 5;
        for (Integer integer : a) {
            System.out.println(integer);
        }


    }


}

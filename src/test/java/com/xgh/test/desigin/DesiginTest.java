package com.xgh.test.desigin;

import com.xgh.test.design.STATUS;
import com.xgh.test.design.Transaction;
import com.xgh.test.design.WalletRpcServiceOne;
import org.junit.jupiter.api.Test;

import javax.transaction.InvalidTransactionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * com.xgh.test.desigin.DesiginTest
 *
 * @author xgh <br/>
 * @description
 * @date 2021年07月08日
 */
public class DesiginTest {

    @Test
    public void testExecute() throws InvalidTransactionException {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        Long orderId = 456L;
        Transaction transaction = new Transaction(null, buyerId, sellerId, productId, orderId);
        transaction.setWalletRpcService(new WalletRpcServiceOne());
        boolean executedResult = transaction.execute();
        assertTrue(executedResult);
        assertEquals(STATUS.EXECUTED, transaction.getStatus());
    }
}

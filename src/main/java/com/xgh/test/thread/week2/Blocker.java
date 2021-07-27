package com.xgh.test.thread.week2;

import java.util.concurrent.Callable;

/**
 * com.xgh.test.thread.week2.Blocker
 *
 * @author xgh <br/>
 * @description 负责对guardAction进行阻塞和唤醒
 * @date 2021年07月27日
 */
public interface Blocker {


    //在保护条件成立时执行目标动作，否则阻塞当前线程，直到保护条件成立
    <V> V callWithGuard(GuardedAction<V> guardedAction)throws Exception;


    //先执行stateOperator,如果返回true则确定唤醒该blocker上阻塞的一共线程
    void signalAfter(Callable<Boolean> stateOperation)throws Exception;

    //直接欢迎blocker上阻塞的一个线程
    void signal()throws Exception;

    //根据stateOperator的是否满足唤醒所有blocker上线程
    void broadcastAfter(Callable<Boolean> stateOperator )throws Exception;
}

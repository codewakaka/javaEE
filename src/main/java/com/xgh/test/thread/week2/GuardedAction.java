package com.xgh.test.thread.week2;

import java.util.concurrent.Callable;

/**
 * com.xgh.test.thread.week2.GuardedAction
 *
 * @author xgh <br/>
 * @description 抽象目标动作，内部包含目标动作所需的保护条件
 * @date 2021年07月27日
 */
public abstract class GuardedAction<V> implements Callable<V> {

    protected final Predicate predicate;

    public GuardedAction(Predicate predicate) {
        this.predicate = predicate;
    }


}

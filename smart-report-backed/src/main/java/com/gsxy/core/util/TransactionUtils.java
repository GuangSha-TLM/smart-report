package com.gsxy.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 事物工具类
 */
@Component
public class TransactionUtils {
    @Autowired
    private DataSourceTransactionManager transactionManager;

    /**
     * @author bryan yang 2023-10-12
     * 开启事物
     * @param isolationLevel 隔离级别
     * @return
     */
    public TransactionStatus begin(int isolationLevel) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        //设置事物隔离
        def.setIsolationLevel(isolationLevel);
        //设置事物传播行为
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //默认食物
        TransactionStatus transactionStatus = transactionManager.getTransaction(def);

        return transactionStatus;
    }

    /**
     * @author bryan yang 2023-10-12
     * 提交事物
     * @param transaction
     * @return
     */
    public void commit(TransactionStatus transaction) {
        //提交事务
        transactionManager.commit(transaction);
    }

    /**
     * @author bryan yang 2023-10-12
     * 回滚事物
     * @param transactionStatus
     * @return
     */
    public void rollback(TransactionStatus transactionStatus) {
        transactionManager.rollback(transactionStatus);
    }

}

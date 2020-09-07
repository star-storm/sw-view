package com.taiji.admin.config;
 
import java.util.Properties;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;
 
/**
 * 
 * teach-src com.taiji.admin.config AdminTransactionConfig.java
 *
 * @author hsl
 *
 * 2018年3月15日 下午3:47:05
 *
 * desc:
 */
@Configuration
public class AdminTransactionConfig {
 
    public static final String transactionExecution = "execution (* com.taiji.admin.service.*.*(..))";
 
    @Autowired
    private PlatformTransactionManager adminTransactionManager;
 
    @Bean
    public TransactionInterceptor transactionInterceptor() {
        Properties attributes = new Properties();
        attributes.setProperty("get*", "PROPAGATION_REQUIRED,-Exception");
        attributes.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
        attributes.setProperty("up*", "PROPAGATION_REQUIRED,-Exception");
        attributes.setProperty("del*", "PROPAGATION_REQUIRED,-Exception");
        TransactionInterceptor txAdvice = new TransactionInterceptor(adminTransactionManager, attributes);
        return txAdvice;
    }
 
    @Bean
    public AspectJExpressionPointcut aspectJExpressionPointcut(){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(transactionExecution);
        return pointcut;
    }
 
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor(){
        //AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        //pointcut.setExpression(transactionExecution);
        //DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        //advisor.setPointcut(pointcut);
        //advisor.setAdvice(transactionInterceptor());
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(transactionExecution);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        Properties attributes = new Properties();
        attributes.setProperty("get*", "PROPAGATION_REQUIRED,-Exception");
        attributes.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
        attributes.setProperty("up*", "PROPAGATION_REQUIRED,-Exception");
        attributes.setProperty("del*", "PROPAGATION_REQUIRED,-Exception");
        TransactionInterceptor txAdvice = new TransactionInterceptor(adminTransactionManager, attributes);
        advisor.setAdvice(txAdvice);
        return advisor;
    }
}
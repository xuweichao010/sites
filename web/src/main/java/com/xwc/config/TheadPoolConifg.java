package com.xwc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 创建人：徐卫超
 * 创建时间：2018/12/29  10:21
 * 业务：
 * 功能：
 */
@Configuration
public class TheadPoolConifg {

    /**
     * 核心数量（最小数量）
     */
    private int callabelCoreSize = 30;
    /**
     * 最大线程数量
     */
    private int callabelMaxSize = 100;
    /**
     * 配置阻塞容量
     */
    private int callabelQueueCapacity = 100;

    /**
     * 同步线程池配置
     *
     * @return
     */
    @Bean("sync")
    public Executor data() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(callabelCoreSize);
        executor.setMaxPoolSize(callabelMaxSize);
        executor.setQueueCapacity(callabelQueueCapacity);
        executor.setThreadNamePrefix("callabel-");
        executor.initialize();
        return executor;
    }
}

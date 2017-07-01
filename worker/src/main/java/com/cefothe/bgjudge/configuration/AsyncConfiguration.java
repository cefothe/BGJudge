package com.cefothe.bgjudge.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 6/2/17.
 */
@EnableAsync
@Configuration
@Profile("!non-async")
public class AsyncConfiguration extends AsyncConfigurerSupport{

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("BGJudge -");
        executor.initialize();
        return executor;
    }
}

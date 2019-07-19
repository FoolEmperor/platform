package pers.lance.platform.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Async Config
 *
 * @author lance
 * @date 2018-11-07
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {


    @Value("${executor.core-pool-size}")
    public Integer corePoolSize;
    @Value("${executor.max-pool-size}")
    public Integer maxPoolSize;
    @Value("${executor.queue-capacity}")
    public Integer queueCapacity;
    @Value("${executor.keep-alive-seconds}")
    public Integer keepAliveSeconds;
    @Value("${executor.await-termination-seconds}")
    public Integer awaitTerminationSeconds;
    @Value("${executor.wait-for-tasks-to-complete-on-shutdown}")
    public Boolean waitForTasksToCompleteOnShutdown;
    @Value("${executor.thread-name-prefix}")
    public String threadNamePrefix;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        threadPoolTaskExecutor.setAwaitTerminationSeconds(awaitTerminationSeconds);
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(waitForTasksToCompleteOnShutdown);
        threadPoolTaskExecutor.setThreadNamePrefix(threadNamePrefix);
        // 线程池对拒绝任务(无线程可用)的处理策略
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

}

package com.nirro.konfi.repository;

import com.nirro.konfi.exception.SourceAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class RefreshableRepositoryScheduler {

    private static final Logger logger = LoggerFactory.getLogger(RefreshableRepositoryScheduler.class);

    private RefreshableRepositoryScheduler() {
    }

    public static ScheduledFuture<?> schedule(Repository repository, Duration duration) {
        return getSingleDaemonThreadScheduledExecutor().scheduleWithFixedDelay(() -> {
            try {
                repository.refresh();
            } catch (SourceAccessException e) {
                logger.atWarn().log("failed to reload properties", e);
            }
            logger.atInfo().log("");
        }, duration.get(ChronoUnit.MILLIS), duration.get(ChronoUnit.MILLIS), TimeUnit.MILLISECONDS);

    }

    private static ScheduledExecutorService getSingleDaemonThreadScheduledExecutor() {
        return Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            thread.setName("ScheduledRepositoryRefresher");
            return thread;
        });
    }
}

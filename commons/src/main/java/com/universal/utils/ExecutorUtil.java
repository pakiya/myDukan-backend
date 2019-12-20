package com.universal.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.universal.constants.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class ExecutorUtil {

    private static final ExecutorService threadPool;

    private static final Logger logger = LoggerFactory.getLogger(ExecutorUtil.class);

    static {
        ThreadFactory factory = new ThreadFactoryBuilder()
                .setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        logger.error("exception occured in thread ", e);

                    }
                }).setNameFormat("kloh-backend-%d").setDaemon(false).build();
        threadPool = Executors.newCachedThreadPool(factory);
    }

    public static <T> Future<T> executeTask(Callable<T> callable) {
        return threadPool.submit(callable);
    }

    public static void submitTask(Runnable runnable) {
        threadPool.execute(runnable);
    }

    public static <T> T getResultFromTask(Future<T> futureTask, boolean optional) {
        if(futureTask == null)
            return null;

        T result = null;
        try {
            if (optional) {
                result = futureTask.get(CommonConstants.DEFAULT_API_TIMEOUT, TimeUnit.MILLISECONDS);
            } else {
                result = futureTask.get();
            }

        } catch (TimeoutException ex) {
            logger.error("time out occured while fetching respose", ex);
            if (!optional) {
                throw new RuntimeException(ex);
            }
        } catch (Exception ex) {
            logger.error("exception occured while fetching respose", ex);
            if (!optional) {
                throw new RuntimeException(ex);
            }
        }

        return result;
    }
}

package top.crossrun.net.listener;

import io.reactivex.Scheduler;

/**
 * 请求和结果处理的线程
 */
public interface ApiSchedulerListener {

    /**
     * 发起请求线程
     * @return
     */
    Scheduler requestScheduler();

    /**
     * 结果处理线程
     * @return
     */
    Scheduler responseScheduler();
}

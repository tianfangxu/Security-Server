package com.mot.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 限制方法运行时常的工具类，需要提供执行的方法（实现Runnable接口）和 终止的方法（StopHolder接口）
 * @author tianfx
 * @date 2021/5/18 4:51
 */
public class RunTimeoutUtil implements Runnable {

    private static RunTimeoutUtil instance;
    private Thread thread;
    private List<Node> list;
    private Node current;
    private volatile boolean flag = true;
    private volatile boolean qd = true;

    //int thread
    static {
        instance = new RunTimeoutUtil();
        instance.thread = new Thread(instance);
        instance.thread.setDaemon(true);
        instance.thread.setName("RUN-TIMEOUT-THREAD");
        instance.list = Collections.synchronizedList(new ArrayList<>());
        instance.thread.start();
    }

    /**
     * @author: tianfx
     * @date: 2021/5/21 2:01 下午
     * @param runnable: 被监听的任务函数
     * @param stopHolder: 任务停止函数
     * @param timeout:  执行任务的超时时间
     */
    public static void excutor(Runnable runnable, StopHolder stopHolder, Long timeout) {
        //add listener
        instance.listener(stopHolder, timeout);
        try {
            //excutor method
            runnable.run();
        } catch (Throwable e) {
            throw e;
        } finally {
            instance.remover();
        }
    }

    private void remover() {
        if (current != null && current.thread == Thread.currentThread()) {
            // current thrad is task ,this task finished without timeout
            list.remove(list.get(0));
            flag = false;
            qd = false;
            current = null;
            LockSupport.unpark(instance.thread);
            if (list.size() > 0) {
                //start next task
                while (!qd) {
                }
                current = list.get(0);
                flag = true;
                qd = false;
                LockSupport.unpark(instance.thread);
            } else {
                current = null;
            }
        }
    }

    private void listener(StopHolder stopHolder, Long timeout) {
        Node node = new Node();
        node.timeout = timeout + System.currentTimeMillis();
        node.thread = Thread.currentThread();
        node.stopHolder = stopHolder;
        if (current == null) {
            // no task is running
            this.current = node;
            this.list.add(node);
            this.qd = false;
            LockSupport.unpark(instance.thread);
        } else {
            this.list.add(node);
            // sort task
            Collections.sort(list, (node1, node2) -> (int) (node1.timeout - node2.timeout));
            if (list.get(0) != current) {
                // first task not currnet task
                flag = false;
                qd = false;
                current = null;
                LockSupport.unpark(instance.thread);
                while (!qd) {
                }
                current = list.get(0);
                flag = true;
                qd = false;
                LockSupport.unpark(instance.thread);
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            if (qd) {
                // have no task will park
                LockSupport.park();
            }
            if (current != null) {
                // park thread with timeout
                LockSupport.parkUntil(current.thread, current.timeout);
            }
            if (current == null) {
                qd = true;
            }
            if (flag) {
                //excutor stop , next task started
                current.stopHolder.stop();
                list.remove(0);
                if (list.size() > 0) {
                    current = list.get(0);
                    qd = false;
                } else {
                    qd = true;
                    current = null;
                }
            }
        }
    }

    class Node {
        Thread thread;
        Long timeout;
        StopHolder stopHolder;
    }

}

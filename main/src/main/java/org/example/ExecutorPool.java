package org.example;

import java.util.Collection;
import java.util.LinkedList;

public class ExecutorPool {

    private final LinkedList<Runnable> tasks = new LinkedList<>();
    private boolean isShutdown = false;

    public ExecutorPool(Integer countExecutor) {
        Runnable runnable = () -> {
            while (!isShutdown) {
                Runnable task;
                synchronized (tasks) {
                    if (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException("Не удалось остановить поток", e);
                        }
                    }
                    task = tasks.removeFirst();
                }
                task.run();
            }
        };
        for (int i = 0; i < countExecutor; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }

    public void addTask(Collection<? extends Runnable> newTasks) {
        if (isShutdown) {
            throw new IllegalStateException("Пул выполнения задач больше не принимает новые задачи");
        }
        synchronized (tasks) {
            tasks.addAll(newTasks);
            tasks.notifyAll();
        }
    }

    public void shutdown() {
        isShutdown = true;
    }
}

package org.example;

public class Task implements Runnable {

    private final String name;
    private final Integer timeExecution;

    public Task(String name, Integer timeExecution) {
        this.name = name;
        this.timeExecution = timeExecution;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(timeExecution);
            System.out.printf("Задача выполнена: %s%n", name);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

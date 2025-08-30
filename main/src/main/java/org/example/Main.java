package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ExecutorPool executorPool = new ExecutorPool(5);
        LinkedList<Task> tasks1 = new LinkedList<>();
        tasks1.add(new Task("Task 01", 5));
        tasks1.add(new Task("Task 02", 10));
        tasks1.add(new Task("Task 03", 20));
        tasks1.add(new Task("Task 04", 15));
        tasks1.add(new Task("Task 05", 6));
        tasks1.add(new Task("Task 06", 1));
        tasks1.add(new Task("Task 07", 120));
        tasks1.add(new Task("Task 08", 500));
        tasks1.add(new Task("Task 09", 32));
        tasks1.add(new Task("Task 10", 68));
        tasks1.add(new Task("Task 11", 70));
        tasks1.add(new Task("Task 12", 63));
        tasks1.add(new Task("Task 13", 28));
        tasks1.add(new Task("Task 14", 96));
        tasks1.add(new Task("Task 15", 36));
        tasks1.add(new Task("Task 16", 79));
        tasks1.add(new Task("Task 17", 11));
        tasks1.add(new Task("Task 18", 325));
        tasks1.add(new Task("Task 19", 96));
        tasks1.add(new Task("Task 20", 89));
        tasks1.add(new Task("Task 21", 2));
        tasks1.add(new Task("Task 22", 5));
        tasks1.add(new Task("Task 23", 5));
        tasks1.add(new Task("Task 24", 96));
        tasks1.add(new Task("Task 25", 74));
        tasks1.add(new Task("Task 26", 96));
        tasks1.add(new Task("Task 27", 32));
        tasks1.add(new Task("Task 28", 78));
        tasks1.add(new Task("Task 29", 96));
        tasks1.add(new Task("Task 30", 14));
        executorPool.addTask(tasks1);
        Thread.sleep(300);
        LinkedList<Task> tasks2 = new LinkedList<>();
        tasks2.add(new Task("Task 20", 89));
        tasks2.add(new Task("Task 21", 2));
        tasks2.add(new Task("Task 22", 5));
        tasks2.add(new Task("Task 23", 5));
        tasks2.add(new Task("Task 24", 96));
        tasks2.add(new Task("Task 25", 74));
        tasks2.add(new Task("Task 26", 96));
        tasks2.add(new Task("Task 27", 32));
        tasks2.add(new Task("Task 28", 78));
        tasks2.add(new Task("Task 29", 96));
        executorPool.addTask(tasks2);
        executorPool.shutdown();
        /*LinkedList<Task> tasks3 = new LinkedList<>();
        tasks3.add(new Task("Task 30", 14));
        executorPool.addTask(tasks3);*/
    }
}
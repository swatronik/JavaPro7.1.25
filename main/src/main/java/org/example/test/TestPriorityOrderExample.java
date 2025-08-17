package org.example.test;

import org.example.anotations.Order;
import org.example.anotations.Test;
import org.example.entity.Assert;

public class TestPriorityOrderExample {


    @Test(priority = 1)
    public void testPriorityA() {
        System.out.println("Тест на приоритет (должен быть 4)");
        Assert.isTrue(true);;
    }

    @Test(priority = 3)
    public void testPriorityB() {
        System.out.println("Тест на приоритет (должен быть 1)");
        Assert.isTrue(true);;
    }

    @Test(priority = 2)
    public void testPriorityC() {
        System.out.println("Тест на приоритет (должен быть 2)");
        Assert.isTrue(true);;
    }

    @Test(priority = 2)
    public void testPriorityD() {
        System.out.println("Тест на приоритет (должен быть 3)");
        Assert.isTrue(true);;
    }

    @Test
    @Order(9)
    public void testOrderA() {
        System.out.println("Тест на порядок (должен быть 4)");
        Assert.isTrue(true);;
    }

    @Test
    @Order(6)
    public void testOrderB() {
        System.out.println("Тест на порядок (должен быть 1)");
        Assert.isTrue(true);;
    }

    @Test
    @Order(7)
    public void testOrderC() {
        System.out.println("Тест на порядок (должен быть 2)");
        Assert.isTrue(true);;
    }

    @Test
    @Order(7)
    public void testOrderD() {
        System.out.println("Тест на порядок (должен быть 3)");
        Assert.isTrue(true);;
    }

    @Test(priority = 9)
    @Order(2)
    public void testPriorityOrderA() {
        System.out.println("Тест на приоритет и порядок (должен быть 1)");
        Assert.isTrue(true);;
    }

    @Test(priority = 8)
    @Order(3)
    public void testPriorityOrderB() {
        System.out.println("Тест на приоритет и порядок (должен быть 2)");
        Assert.isTrue(true);;
    }

    @Test(priority = 8)
    @Order(4)
    public void testPriorityOrderC() {
        System.out.println("Тест на приоритет и порядок (должен быть 3)");
        Assert.isTrue(true);;
    }

    @Test(priority = 8)
    @Order(4)
    public void testPriorityOrderD() {
        System.out.println("Тест на приоритет и порядок (должен быть 4)");
        Assert.isTrue(true);;
    }
}

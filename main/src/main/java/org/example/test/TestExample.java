package org.example.test;

import org.example.anotations.*;
import org.example.entity.Assert;

public class TestExample {

    @BeforeSuite
    public static void beforeSuite() {
        System.out.println("ВСЕ Тесты запустились");
    }

    @AfterSuite
    public static void afterSuite() {
        System.out.println("ВСЕ Тесты завершились");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Тест запустился");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("Тест завершился");
    }

    public void testWithoutAnnotation() {
        System.out.println("Тест без аннотации");
        Assert.isTrue(true);
    }

    @Test("Успешный тест")
    private void testSuccess() {
        System.out.println("Успешный тест");
        Assert.isTrue(true);
    }

    @Test
    public void testSuccessWithoutName() {
        System.out.println("Тест без имени");
        Assert.isTrue(true);
    }

    @Test("Не успешный тест")
    public void testFailed() {
        System.out.println("Не успешный тест");
        Assert.isTrue(false);
    }

    @Test("Тест с ошибкой")
    public void testError() {
        System.out.println("Тест с ошибкой");
        throw new RuntimeException("Непредвидимая ошибка которая могла случится с тестом");
    }

    @Disabled
    @Test("Пропущенный тест")
    public void testDisabled() {
        System.out.println("Пропущенный тест");
        Assert.isTrue(true);
    }
}

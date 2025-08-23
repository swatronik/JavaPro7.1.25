package org.example.entity;

public enum TestResult {
    SUCCESS("Тест выполнен успешно"),
    FAILED("Условие теста провалено"),
    ERROR("Тест упал с произвольным исключением"),
    SKIPPED("Тест не исполнялся");

    private final String description;

    TestResult(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

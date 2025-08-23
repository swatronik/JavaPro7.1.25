package org.example.entity;

public class Test {

    private TestResult testResult;
    private String nameTest;
    private Throwable exception;

    public Test(TestResult testResult, String nameTest, Throwable exception) {
        this.testResult = testResult;
        this.nameTest = nameTest;
        this.exception = exception;
    }

    public Test(TestResult testResult, String nameTest) {
        this.testResult = testResult;
        this.nameTest = nameTest;
    }

    public TestResult getTestResult() {
        return testResult;
    }

    public String getNameTest() {
        return nameTest;
    }

    public Throwable getException() {
        return exception;
    }

    @Override
    public String toString() {
        return "Test{" +
                "testResult=" + testResult +
                ", nameTest='" + nameTest + '\'' +
                (getException() != null ? ", exception=" + getException() : "") +
                '}';
    }
}

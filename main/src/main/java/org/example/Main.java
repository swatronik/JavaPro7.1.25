package org.example;


import org.example.entity.Test;
import org.example.entity.TestResult;
import org.example.entity.TestRunner;
import org.example.test.TestExample;
import org.example.test.TestPriorityOrderExample;

import java.util.ArrayList;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<TestResult, ArrayList<Test>> testResultArrayListMap = TestRunner.runTests(TestExample.class, TestPriorityOrderExample.class);
        testResultArrayListMap.values().stream().flatMap(ArrayList::stream).forEach(System.out::println);
    }
}
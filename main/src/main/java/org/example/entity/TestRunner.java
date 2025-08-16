package org.example.entity;

import org.example.anotations.*;
import org.example.exceptions.BadTestClassError;
import org.example.exceptions.TestAssertionError;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestRunner {

    private static boolean methodHasAnnotation(Method method, Class<? extends Annotation> annotation) {
        return Arrays.stream(method.getDeclaredAnnotations())
                .anyMatch(actualAnnotation -> actualAnnotation.annotationType().equals(annotation));
    }

    private static String getNameTest(Method method) {
        String testName = method.getAnnotation(org.example.anotations.Test.class).value();
        return testName.isEmpty() ? method.getName() : testName;
    }

    public static Map<TestResult, ArrayList<Test>> runTests(Class<?>... clazz) {
        Map<TestResult, ArrayList<Test>> result = new HashMap<>();
        result.put(TestResult.SUCCESS, new ArrayList<>());
        result.put(TestResult.FAILED, new ArrayList<>());
        result.put(TestResult.ERROR, new ArrayList<>());
        result.put(TestResult.SKIPPED, new ArrayList<>());

        ArrayList<Method> beforeSuite = new ArrayList<>();
        ArrayList<Method> afterSuite = new ArrayList<>();

        for (Class<?> aClass : clazz) {
            Method[] declaredMethods = aClass.getDeclaredMethods();
            Arrays.stream(declaredMethods).forEach(method -> {
                if (methodHasAnnotation(method, BeforeSuite.class)) {
                    beforeSuite.add(method);
                }
                if (methodHasAnnotation(method, AfterSuite.class)) {
                    afterSuite.add(method);
                }
            });
        }

        beforeSuite.forEach(method -> {
            try {
                method.invoke(null);
            } catch (NullPointerException e){
                throw new BadTestClassError("Ошибка beforeSuite метода (метод должен быть static): " + method.getName());
            } catch (IllegalAccessException e) {
                throw new BadTestClassError("Ошибка запуска beforeSuite метода (метод должен быть public): " + method.getName());
            } catch (InvocationTargetException e) {
                throw new BadTestClassError("Ошибка beforeSuite метода: " + method.getName());
            }
        });

        for (Class<?> aClass : clazz) {
            Object testObject;
            try {
                testObject = aClass.getConstructor().newInstance();
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            ArrayList<Method> beforeEach = new ArrayList<>();
            ArrayList<Method> afterEach = new ArrayList<>();

            Method[] declaredMethods = aClass.getDeclaredMethods();
            Arrays.stream(declaredMethods).forEach(method -> {
                if (methodHasAnnotation(method, BeforeEach.class)) {
                    beforeEach.add(method);
                }
                if (methodHasAnnotation(method, AfterEach.class)) {
                    afterEach.add(method);
                }
            });

            ArrayList<Method> tests = new ArrayList<>();
            Arrays.stream(declaredMethods).forEach(method -> {
                if (methodHasAnnotation(method, org.example.anotations.Test.class)) {
                    if (methodHasAnnotation(method, org.example.anotations.Disabled.class)) {
                        result.get(TestResult.SKIPPED).add(new Test(TestResult.SKIPPED, getNameTest(method)));
                    } else {
                        tests.add(method);
                    }
                }
            });

            tests.sort((el1, el2) -> {
                int testPriority1 = el1.getAnnotation(org.example.anotations.Test.class).priority();
                int testPriority2 = el2.getAnnotation(org.example.anotations.Test.class).priority();
                int comparePriority = Integer.compare(testPriority1, testPriority2);
                if (comparePriority != 0) {
                    return -1 * comparePriority;
                }
                Order testOrderAnnotation1 = el1.getAnnotation(org.example.anotations.Order.class);
                Order testOrderAnnotation2 = el2.getAnnotation(org.example.anotations.Order.class);
                int testOrder1 = testOrderAnnotation1 == null ? 5 : testOrderAnnotation1.value();
                int testOrder2 = testOrderAnnotation2 == null ? 5 : testOrderAnnotation2.value();
                int compareOrder = Integer.compare(testOrder1, testOrder2);
                if (compareOrder != 0) {
                    return compareOrder;
                }
                return getNameTest(el1).compareTo(getNameTest(el2));
            });

            tests.forEach(test -> {
                beforeEach.forEach(beforeMethod -> {
                    try {
                        beforeMethod.invoke(testObject);
                    } catch (IllegalAccessException e) {
                        throw new BadTestClassError("Ошибка beforeEach метода (метод должен быть public): " + beforeMethod.getName());
                    } catch (InvocationTargetException e) {
                        throw new BadTestClassError("Ошибка запуска beforeEach метода: " + beforeMethod.getName());
                    }
                });

                try {
                    test.invoke(testObject);
                    result.get(TestResult.SUCCESS).add(new Test(TestResult.SUCCESS, getNameTest(test)));
                } catch (InvocationTargetException e) {
                    if (e.getCause() instanceof TestAssertionError) {
                        result.get(TestResult.FAILED).add(new Test(TestResult.FAILED, getNameTest(test), e.getCause()));
                    } else {
                        result.get(TestResult.ERROR).add(new Test(TestResult.ERROR, getNameTest(test), e.getCause()));
                    }
                } catch (IllegalAccessException e) {
                    throw new BadTestClassError("Ошибка запуска теста (тест должен быть public): " + getNameTest(test));
                }

                afterEach.forEach(afterMethod -> {
                    try {
                        afterMethod.invoke(testObject);
                    } catch (IllegalAccessException e) {
                        throw new BadTestClassError("Ошибка запуска afterEach метода (метод должен быть public): " + afterMethod.getName());
                    } catch (InvocationTargetException e) {
                        throw new BadTestClassError("Ошибка запуска afterEach метода: " + afterMethod.getName());
                    }
                });
            });
        }

        afterSuite.forEach(method -> {
            try {
                method.invoke(null);
            } catch (NullPointerException e){
                throw new BadTestClassError("Ошибка afterSuite метода (метод должен быть static): " + method.getName());
            } catch (IllegalAccessException e) {
                throw new BadTestClassError("Ошибка запуска afterSuite метода (метод должен быть public): " + method.getName());
            } catch (InvocationTargetException e) {
                throw new BadTestClassError("Ошибка afterSuite метода: " + method.getName());
            }
        });
        return result;
    }
}

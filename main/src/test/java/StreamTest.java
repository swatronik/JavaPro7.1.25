import org.example.entity.Employee;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTest {

    /**
     * Дан список строк. Сформируйте такую версию списка, где не будет null, пустых строк и дубликатов.
     * [null, "", "ABC", "ABC","QQ"] => ["ABC","QQ"]
     */
    @Test
    public void exercise1Test() {
        List<String> param = Arrays.asList(null, "", "ABC", "ABC", "QQ");
        List<String> expectResult = Arrays.asList("ABC", "QQ");

        List<String> actualResult = param.stream()
                .filter(el -> el != null && !el.isEmpty())
                .distinct()
                .collect(Collectors.toList());

        Assert.assertEquals(actualResult, expectResult);
    }

    /**
     * Дан список строк. Посчитайте количество разных букв во всех строках.
     * ["ABC","CDE", "EE"] => 5 (так как уникальные буквы это ABCDE)
     */
    @Test
    public void exercise2Test() {
        List<String> param = Arrays.asList("ABC", "CDE", "EE");
        long expectResult = 5;

        long actualResult = param.stream()
                .flatMapToInt(String::chars)
                .distinct()
                .count();

        Assert.assertEquals(actualResult, expectResult);
    }

    /**
     * Дан список строк. Верните самое длинное слово из списка.
     * ["ABC","CDEF", "EE"] => "CDEF"
     */
    @Test
    public void exercise3Test() {
        List<String> param = Arrays.asList("ABC", "CDEF", "EE");
        String expectResult = "CDEF";

        String actualResult = param.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse("");

        Assert.assertEquals(actualResult, expectResult);
    }

    /**
     * Дан список чисел. Найдите в списке 3-е наибольшее число
     * [5, 2, 10, 9, 4, 3, 10, 1, 13] => 10
     */
    @Test
    public void exercise4Test() {
        List<Integer> param = Arrays.asList(5, 2, 10, 9, 4, 3, 10, 1, 13);
        Integer expectResult = 10;

        Integer actualResult = param.stream()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElse(Integer.MIN_VALUE);

        Assert.assertEquals(actualResult, expectResult);
    }

    /**
     * Дан список объектов типа Сотрудник (имя, возраст, должность),
     * необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста
     */
    @Test
    public void exercise5Test() {
        List<Employee> param = Arrays.asList(
                new Employee("Иван", 52, "Инженер"),
                new Employee("Петр", 49, "Инженер"),
                new Employee("Олег", 28, "Инженер"),
                new Employee("Вадим", 37, "Бухгалтер"),
                new Employee("Семен", 35, "Инженер"),
                new Employee("Максим", 68, "Уборщик")
        );
        List<Employee> expectResult = Arrays.asList(
                new Employee("Иван", 52, "Инженер"),
                new Employee("Петр", 49, "Инженер"),
                new Employee("Семен", 35, "Инженер")
        );

        List<Employee> actualResult = param.stream()
                .filter(employee -> employee.getFunction().equals("Инженер"))
                .sorted((em1, em2) -> Integer.compare(em2.getAge(), em1.getAge()))
                .limit(3)
                .collect(Collectors.toList());

        Assert.assertEquals(actualResult, expectResult);
    }

    /**
     * Дан список объектов типа Сотрудник (имя, возраст, должность), посчитайте средний возраст сотрудников с должностью «Инженер»
     */
    @Test
    public void exercise6Test() {
        List<Employee> param = Arrays.asList(
                new Employee("Иван", 52, "Инженер"),
                new Employee("Петр", 49, "Инженер"),
                new Employee("Олег", 28, "Инженер"),
                new Employee("Вадим", 37, "Бухгалтер"),
                new Employee("Семен", 35, "Инженер"),
                new Employee("Максим", 68, "Уборщик")
        );
        Double expectResult = 41.0;

        Double actualResult = param.stream()
                .filter(employee -> employee.getFunction().equals("Инженер"))
                .mapToDouble(Employee::getAge)
                .average()
                .orElse(0);

        Assert.assertEquals(actualResult, expectResult);
    }

    /**
     * Дана строка, которая представляет собой предложение из нескольких слов.
     * Необходимо получить Map, в которой ключом будет число, означающее длину слова, а значение это список слов указанной длины.
     * Пробелы и другие разделители необходимо убрать. Слова в разном регистре считаются одним и тем же словом.
     * "Мама мыла Окно, окно было довольно" => [4: мама, мыла, окно, было; 8: довольно]
     */
    @Test
    public void exercise7Test() {
        String param = "Мама мыла Окно, окно было довольно";
        Map<Integer, List<String>> expectResult = Map.of(4, List.of("мама", "мыла", "окно", "было"), 8, List.of("довольно"));


        String[] split = param.split("[, ]");
        Map<Integer, List<String>> actualResult = Arrays.stream(split)
                .filter(el -> el != null && !el.isEmpty())
                .map(String::toLowerCase)
                .distinct()
                .collect(Collectors.groupingBy(String::length));

        Assert.assertEquals(actualResult, expectResult);
    }

    /**
     * Дан список строк. Каждая строка содержит предложение. Верните список самых длинных слов.
     * ["Мама мыла Окно, окно было довольно", "кровать"] => [довольно]
     * (довольно – самое длинное слово и оно одно)
     */
    @Test
    public void exercise8Test() {
        List<String> param = List.of("Мама мыла Окно, окно было довольно", "кровать");
        List<String> expectResult = List.of("довольно");

        List<String> actualResult = param.stream()
                .map(elem -> elem.split("[, ]"))
                .flatMap(Arrays::stream)
                .filter(el -> el != null && !el.isEmpty())
                .collect(Collectors.groupingBy(String::length))
                .entrySet()
                .stream()
                .max(Comparator.comparingInt(Map.Entry::getKey))
                .orElse(new HashMap.SimpleEntry<>(0, List.of()))
                .getValue();

        Assert.assertEquals(actualResult, expectResult);
    }
}

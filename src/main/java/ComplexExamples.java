import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ComplexExamples {

    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }

        @Override
        public String toString() {
            return "id=" + id + ", name=" + name;
        }
    }

    private static Person[] RAW_DATA = new Person[]{
            //new Person(10, "Harry"),
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };
        /*  Raw data:

        0 - Harry
        0 - Harry
        1 - Harry
        2 - Harry
        3 - Emily
        4 - Jack
        4 - Jack
        5 - Amelia
        5 - Amelia
        6 - Amelia
        7 - Amelia
        8 - Amelia

        **************************************************

        Duplicate filtered, grouped by name, sorted by name and id:

        Amelia:
        1 - Amelia (5)
        2 - Amelia (6)
        3 - Amelia (7)
        4 - Amelia (8)
        Emily:
        1 - Emily (3)
        Harry:
        1 - Harry (0)
        2 - Harry (1)
        3 - Harry (2)
        Jack:
        1 - Jack (4)
     */

    public static void main(String[] args) {
        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();

        /*
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

            Что должно получиться
                Key: Amelia
                Value:4
                Key: Emily
                Value:1
                Key: Harry
                Value:3
                Key: Jack
                Value:1
         */
        Map<String, Long> personAfterFormatting = Arrays.asList(RAW_DATA).stream()
                .distinct()
                .sorted(Comparator.comparing(person -> person.id))
                .collect(Collectors.groupingBy(Person::getName, Collectors.counting()));
        for(Map.Entry<String, Long> person : personAfterFormatting.entrySet()) {
            System.out.println("Key: " + person.getKey() + "\nValue: " + person.getValue());
        }
        System.out.println();


        /*
        Task2
            [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
         */
        int[] array = new int[]{3, 4, 2, 7};
        int target = 10;
        List<Integer> resultPair = getPairOfNumberByTarget(array, target);
        System.out.println(resultPair);
        System.out.println();


        /*
        Task3
            Реализовать функцию нечеткого поиска
            
                    fuzzySearch("car", "ca6$$#_rtwheel"); // true
                    fuzzySearch("cwhl", "cartwheel"); // true
                    fuzzySearch("cwhee", "cartwheel"); // true
                    fuzzySearch("cartwheel", "cartwheel"); // true
                    fuzzySearch("cwheeel", "cartwheel"); // false
                    fuzzySearch("lw", "cartwheel"); // false
         */
        System.out.println(fuzzySearch("car", "ca6$$#_rtwheel"));
        System.out.println(fuzzySearch("cwhl", "cartwheel"));
        System.out.println(fuzzySearch("cwhee", "cartwheel"));
        System.out.println(fuzzySearch("cartwheel", "cartwheel"));
        System.out.println(fuzzySearch("cwheeel", "cartwheel"));
        System.out.println(fuzzySearch("lw", "cartwheel"));

    }

    // task2
    public static List<Integer> getPairOfNumberByTarget(int[] array, int target) {
        List<Integer> resultPair = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int difference = target - array[i];
            if (map.containsKey(difference)) {
                resultPair.addAll(Arrays.asList(difference, array[i]));
                break;
            }
            map.put(array[i], difference);
        }
        return resultPair;
    }

    // task3
    public static boolean fuzzySearch(String targetWord, String sourceWord) {
        int pointerTarget = 0;
        int pointerSource = 0;
        while (pointerTarget < targetWord.length() && pointerSource < sourceWord.length()) {
            if (targetWord.charAt(pointerTarget) == sourceWord.charAt(pointerSource)) {
                pointerTarget++;
            }
            pointerSource++;
        }
        if (pointerTarget == targetWord.length()) {
            return true;
        }
        return false;
    }
}

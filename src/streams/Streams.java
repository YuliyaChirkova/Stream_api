package streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.partitioningBy;

public class Streams {
    public static void main(String[] args) {

      /*  https://www.codingame.com/playgrounds/20782/java-guild-meeting-52018/streams---micro-katas*/

        // Task # 1  Flatten this multidimensional collection
        List<List<String>> collectionTask3 = asList(asList("Viktor", "Farcic"), asList("John", "Doe", "Third"));
        List<String> transform = collectionTask3.stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(transform);


        // Task # 2 Get the oldest person from the collection
        Person sara = new Person("Sara", 4, "Norwegian");
        Person viktor = new Person("Viktor", 40, "Serbian");
        Person eva = new Person("Eva", 42, "Norwegian");
        List<Person> people = asList(sara, eva, viktor);
        Person oldestPerson = people.stream().max(Comparator.comparingInt(Person::getAge)).get();
        System.out.println(oldestPerson);

        // Task # 3 Sum all elements of a collection, try to use the reduce operator with identity parameter instead of an IntStream
        List<Integer> numbers = asList(1, 2, 3, 4, 5);
        int sum = numbers.stream().reduce(Integer::sum).get();
        System.out.println(sum);

        // Task # 4 Get the names of all kids under the age of 18
        Person anna = new Person("Anna", 5);
        List<Person> collection = asList(sara, eva, viktor, anna);
        Set<String> people2 = collection.stream().filter(s -> s.getAge() < 18).map(Person::getName).collect(Collectors.toSet());
        System.out.println(people2);

        // Task # 5 Partition these people into adults and kids, you'll need a special collector for this one
        List<Person> collection3 = asList(sara, eva, viktor);
        Map<Boolean, List<Person>> map = collection3.stream().collect(partitioningBy(p -> p.getAge() >= 18));
        System.out.println(map);

        // Task # 6  Group these people by nationality, same kind as the previous exercise
        Map<String, List<Person>> map2 = collection3.stream().collect(Collectors.groupingBy(Person::getNationality));
        System.out.println(map2);

        // Task # 7  Return a comma-separated string of all these people's names
        String names = collection3.stream().map(Person::getName).collect(Collectors.joining(", ", " ", " "));
        System.out.println(names);



        /*Tasks from another sources */

        // Task # 1
        List<String> list = Stream.of("Nina", "Tamara", "Zara", "Misha").map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(list);

        // Task # 2
        // option 1st
        long numberOfLetter = list.stream().filter(s -> s.length() > 5).map(String::length).reduce(Integer::sum).orElse(0);
        System.out.println(numberOfLetter);

        // option 2nd
        long numberOfLetter2 = list.stream().filter(s -> s.length() > 5).mapToInt(String::length).sum();
        System.out.println(numberOfLetter);


        // Task # 3
        List<Integer> numbersChallenge = asList(1, 2, 3, 4, 5);

        String challenge = numbersChallenge.stream().map(p -> {
            if (p % 2 == 0) {
                return "e" + p;
            } else {
                return "o" + p;
            }
        }).collect(Collectors.joining(","));
        System.out.println("-------------" + challenge);


        // Task # 10 devgenius
        Map<String, List<Person>> map3 = people.stream().collect(Collectors.groupingBy(Person::getNationality));

        // Task # 11 devgenius
        Employee emp1 = new Employee("Nick", 23, "Ger", 1000);
        Employee emp2 = new Employee("Mike", 32, "Den", 2000);
        Employee emp3 = new Employee("Sara", 37, "Pol", 5000);
        Employee emp4 = new Employee("Ben", 42, "Eng", 4000);
        List<Employee> employeeList = asList(emp1, emp2, emp3, emp4);
        double averageSalary = employeeList.stream().mapToInt(Employee::getSalary).average().getAsDouble();
        System.out.println(averageSalary);


        List <String> collection1 = asList("a1", "a2", "a3", "a1");
        List <String> collection2 = asList("1,2,0", "4,5");

        int [] number = collection1.stream().mapToInt(s -> Integer.parseInt(s.substring(1))).toArray();
        System.out.println((Arrays.toString(number)));

        String [] number2 = collection2.stream().flatMap((p) -> Arrays.stream(p.split(","))).toArray(String[]::new);
        System.out.println((Arrays.toString(number2)));

        int sum1 = collection2.stream().flatMap((p) -> Arrays.stream(p.split(",")).map(Integer::parseInt)).reduce(Integer::sum).orElse(0);
        System.out.println(sum1);
    }
}

class Person {

    private String name;
    private int age;
    private String nationality;

    public Person(String name, int age, String nationality) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
    }

    public Person(String name, int age) {
        this(name, age, "");
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nationality='" + nationality + '\'' +
                '}';
    }

    public String getNationality() {
        return nationality;
    }


}

class Employee {
    private String name;
    private int age;
    private String nationality;
    private int salary;

    public Employee(String name, int age, String nationality, int salary) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nationality='" + nationality + '\'' +
                ", salary=" + salary +
                '}';
    }
}

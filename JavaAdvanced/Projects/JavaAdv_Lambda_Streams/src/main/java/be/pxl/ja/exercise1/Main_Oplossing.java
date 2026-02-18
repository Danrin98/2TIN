package be.pxl.ja.exercise1;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main_Oplossing {
    public static void main(String[] args) {
        List<Student> students = StudentList.createStudents();

        // 1. Welke studenten zijn er vandaag jarig?
        System.out.println("=== Studenten die vandaag jarig zijn ===");
        students.stream()
                .filter(s -> s.getDateOfBirth().getDayOfMonth() == LocalDate.now().getDayOfMonth()
                        && s.getDateOfBirth().getMonthValue() == LocalDate.now().getMonthValue())
                .map(Student::getName)
                .forEach(System.out::println);

        // 2. Toon alle gegevens van de student met de naam Carol.
        System.out.println("\n=== Gegevens van Carol ===");
        students.stream()
                .filter(s -> s.getName().equalsIgnoreCase("Carol"))
                .forEach(s -> System.out.println(
                        s.getName() + " " + s.getGraduationYear() + " " + s.getScore() + " " + s.getDateOfBirth()));

        // 3. Hoeveel studenten studeerden af in 2017?
        long count2017 = students.stream()
                .filter(s -> s.getGraduationYear() == 2017)
                .count();
        System.out.println("\nAantal studenten afgestudeerd in 2017: " + count2017);

        // 4. Hoogste score + wie behaalde die?
        int highestScore = students.stream()
                .mapToInt(Student::getScore)
                .max()
                .orElse(0);
        System.out.println("\nHoogste score: " + highestScore);
        students.stream()
                .filter(s -> s.getScore() == highestScore)
                .map(Student::getName)
                .forEach(name -> System.out.println("Behaald door: " + name));

        // 5. Wie is de oudste persoon?
        System.out.println("\n=== Oudste student ===");
        students.stream()
                .min(Comparator.comparing(Student::getDateOfBirth))
                .ifPresent(s -> System.out.println(s.getName() + " (" + s.getDateOfBirth() + ")"));

        // 6. Namen gescheiden door komma
        String allNames = students.stream()
                .map(Student::getName)
                .collect(Collectors.joining(", "));
        System.out.println("\nAlle namen: " + allNames);

        // 7. Jongste student van afstudeerjaar 2018
        System.out.println("\n=== Jongste student van 2018 ===");
        students.stream()
                .filter(s -> s.getGraduationYear() == 2018)
                .max(Comparator.comparing(Student::getDateOfBirth))
                .ifPresent(s -> System.out.println(s.getName() + " (" + s.getDateOfBirth() + ")"));

        // 8. Map met per afstudeerjaar de gemiddelde score
        System.out.println("\n=== Gemiddelde score per afstudeerjaar ===");
        Map<Integer, Double> avgPerYear = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getGraduationYear,
                        Collectors.averagingInt(Student::getScore)
                ));
        avgPerYear.forEach((year, avg) -> System.out.println(year + ": " + String.format("%.2f", avg)));

        // 9. Sorteer: eerst recentste afstudeerjaar, dan per jaar op score (hoog naar laag)
        System.out.println("\n=== Gesorteerde studenten ===");
        students.stream()
                .sorted(Comparator.comparing(Student::getGraduationYear).reversed()
                        .thenComparing(Student::getScore, Comparator.reverseOrder()))
                .forEach(s -> System.out.println(s.getName() + " - " + s.getGraduationYear() + " - " + s.getScore()));
    }
}
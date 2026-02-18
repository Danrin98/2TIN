package be.pxl.ja.exercise1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student> students = StudentList.createStudents();

        // 1.  Welke studenten zijn er vandaag jarig. Toon hun naam. Je verandert best een
        // geboortedata van de studenten om je stream uit te testen.

        System.out.println("\n=== Studenten die vandaag jarig zijn ===");
        students.stream()
                .filter(student -> student.getDateOfBirth().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()
                 && student.getDateOfBirth().getMonth() == LocalDateTime.now().getMonth())
                .map(Student::getName)
                .forEach(System.out::println);

        // 2. Toon alle gegevens van de student met de naam Carol.

        System.out.println("\n=== Gegevens van Carol ===");
        students.stream()
                .filter(student -> student.getName().equals("Carol"))
                .forEach(student -> System.out.println("Name: " + student.getName() + " Birthdate: " + student.getDateOfBirth()
                        + " Graduationyear: " + student.getGraduationYear() + " Score: " + student.getScore()));

        // 3.  Hoeveel studenten studeerden af in 2017?

        System.out.println("\n=== Studenten afgestudeerd in 2017 ===");
        List<String> NameList = students.stream()
                .filter(student -> student.getGraduationYear() == 2017)
                .map(Student::getName).toList();
        NameList.forEach(System.out::println);
        System.out.println("Aantal studenten: " + NameList.size());

        // 4. Wat is de hoogste score ooit behaald? Wie behaalde deze hoogste score ooit?

        System.out.println("\n=== Hoogste score ooit behaald ===");
        Student highest = students.stream()
                .sorted()
                .findFirst().get();
        System.out.println("Hoogste score: " + highest.getScore() + ", behaald door: " + highest.getName());

        // 8.  Maak een map met per afstudeerjaar de gemiddelde score.
        System.out.println("\n=== Gemiddelde score per afstudeerjaar ===");
        Map<Integer, Double> avgPerYear = students.stream()
                .collect(Collectors.groupingBy(Student::getGraduationYear,
                        Collectors.averagingInt(Student::getScore)));
        avgPerYear.forEach((year, avg) -> System.out.println(year + ": " + String.format("%.2f", avg)));
    }
}

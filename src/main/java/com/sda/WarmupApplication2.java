package com.sda;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WarmupApplication2 {
    public static void main(String[] args) {
        List<Student> studentsList = createStudentsList();

        // 1. wypisac tylko kobiety z age powyzej 20
        studentsList.stream()
                .filter(e -> e.getGender() == Gender.FEMALE)
                .filter(e -> e.getAge() > 20)
                .forEach(e -> System.out.println(e));

        // 2. Wypisac tylko studentow o nazwisku Nowak
        studentsList.stream()
                .filter(e -> "Nowak".equals(e.getLastName()))
                .forEach(e -> System.out.println(e));

        // 3. wypisać ilość studentów majacych powyzej 20 lat
        System.out.println(studentsList.stream()
                .filter(e -> e.getAge() > 20)
                .count());

        // 4. wypisac srednia wieku (mapToInt)
        System.out.println(studentsList.stream()
                .mapToInt(e -> e.getAge())
                .average()
                .getAsDouble());

        // *5. wypisac dla kazdego studenta srednia ocen
        studentsList.stream()
                .forEach(student -> {
                    Map<Subject, Double> grades = student.getGrades();
                    System.out.println("Student " + student.getFirstName() + " " + student.getLastName());
                    System.out.println(grades.entrySet().stream()
                            .mapToDouble(entry -> entry.getValue())
                            .average()
                            .getAsDouble());
                    System.out.println();
                });

        // *6. wypisac tylko studentow ktorzy maja zaliczone wszystkie przedmioty
        studentsList.stream()
                .filter(student -> student.getGrades().entrySet().stream()
                        .map(grade -> grade.getValue())
                        .allMatch(grade -> grade >= 3.0))
                .forEach(student -> System.out.println(student));
    }

    private static List<Student> createStudentsList() {
        Student student = Student.builder()
                .firstName("Szymon")
                .lastName("Nowak")
                .gender(Gender.MALE)
                .age(24)
                .build()
                .addGrade(Subject.MATH, 3.5)
                .addGrade(Subject.PROGRAMMING, 4.5);

        Student student2 = Student.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .gender(Gender.MALE)
                .age(22)
                .build()
                .addGrade(Subject.MATH, 2.0)
                .addGrade(Subject.PROGRAMMING, 3.5);

        Student student3 = Student.builder()
                .firstName("Anna")
                .lastName("Wisniwska")
                .gender(Gender.FEMALE)
                .age(21)
                .build()
                .addGrade(Subject.MATH, 5.0)
                .addGrade(Subject.PROGRAMMING, 4.5);

        Student student4 = Student.builder()
                .firstName("Karolina")
                .lastName("Nowak")
                .gender(Gender.FEMALE)
                .age(19)
                .build()
                .addGrade(Subject.MATH, 4.0)
                .addGrade(Subject.PROGRAMMING, 2.0);

        return Arrays.asList(student, student2, student3, student4);
    }
}

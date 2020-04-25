package sample.entity.studentgroup;

import sample.entity.student.Student;

import java.util.Set;

public class StudentGroup {

    private Long id;

    private String name;

    Set<Student> students;
}

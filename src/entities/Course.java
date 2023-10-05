package entities;

import java.util.List;
import java.util.ArrayList;

public abstract class Course {
    private String name;
    private Teacher teacher;
    private List<Student> students;

    public Course(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
        this.students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Teacher getTeacher() {
        return teacher;
    }
    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Course Name: ").append(name).append("\n");
        sb.append("Teacher: ").append(teacher.getName()).append("\n");
        sb.append("Students: ");
        for (Student student : students) {
            sb.append(student.getName()).append(" ");
        }
        return sb.append("\n").toString();
    }

}

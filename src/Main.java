import entities.Course;
import entities.CourseFactory;
import entities.Student;
import entities.Teacher;
import managers.CourseManager;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();

        Student student1 = new Student("Ivan");
        students.add(student1);
        Student student2 = new Student("Sergey");
        students.add(student2);
        Student student3 = new Student("Alexey");
        students.add(student3);
        Student student4 = new Student("Katya");
        students.add(student4);
        Student student5 = new Student("Andrey");
        students.add(student5);
        Student student6 = new Student("Olga");
        students.add(student6);
        Student student7 = new Student("Dmitriy");
        students.add(student7);

        Teacher teacher1 = new Teacher("Ivanov");
        Teacher teacher2 = new Teacher("Petrov");

        List<Course> courses = new ArrayList<>();
        Course MathCourse = CourseFactory.getClassFromFactory("math",teacher1);
        Course EnglishCourse = CourseFactory.getClassFromFactory("english",teacher2);
        Course HistoryCourse = CourseFactory.getClassFromFactory("history",teacher1);

        MathCourse.addStudent(student1);
        MathCourse.addStudent(student2);
        MathCourse.addStudent(student3);
        MathCourse.addStudent(student4);
        EnglishCourse.addStudent(student5);
        EnglishCourse.addStudent(student6);
        HistoryCourse.addStudent(student7);

        courses.add(MathCourse);
        courses.add(EnglishCourse);
        courses.add(HistoryCourse);

        System.out.println("Before sorting:");
        CourseManager.printCoursesInfo(courses);

        CourseManager.sortCourses(courses,"courseName");

        System.out.println("\nafter sorting by course name:");
        CourseManager.printCoursesInfo(courses);

        CourseManager.sortCourses(courses,"numStudents");
        System.out.println("\nafter sorting by number of students:");
        CourseManager.printCoursesInfo(courses);

        System.out.println("\nCourses where teacher is " + teacher1.getName());
        CourseManager.printCoursesInfo(CourseManager.findCoursesByTeacher(courses,teacher1));
    }
}
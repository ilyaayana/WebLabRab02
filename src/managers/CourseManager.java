package managers;
import entities.Archive;
import entities.Course;
import entities.Record;
import entities.Student;
import entities.Teacher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class CourseManager {

    public static void setGrade(Student student, Course course, int grade)
    {
        Record record = new Record(course, student, grade);
        Archive.getInstance().addRecord(record);
        course.removeStudent(student);
    }
    public static void sortCourses(List<Course> courses, String sortBy) {
        switch (sortBy) {
            case "courseName":
                Collections.sort(courses, Comparator.comparing(Course::getName));
                break;
            case "teacherName":
                Collections.sort(courses, Comparator.comparing(course -> course.getTeacher().getName()));
                break;
            case "numStudents":
                Collections.sort(courses, Comparator.comparingInt(course -> course.getStudents().size()));
                break;
            default:
                throw new IllegalArgumentException("Invalid sort criteria.");
        }
    }
    public static List<Course> findCoursesByTeacher(List<Course> courseList, Teacher teacher) {
        List<Course> matchingCourses = new ArrayList<>();

        for (Course course : courseList) {
            if (course.getTeacher().equals(teacher)) {
                matchingCourses.add(course);
            }
        }

        return matchingCourses;
    }
    public static void printCoursesInfo(List<Course> courses)
    {
        for(Course course:courses){
            System.out.println(course.toString());
        }
    }


}

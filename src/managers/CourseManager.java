package managers;
import entities.Archive;
import entities.Course;
import entities.Record;
import entities.Student;
import entities.Teacher;

import DAO.*;

import java.util.Date;
import java.util.*;


public class CourseManager {

    private static TeacherDaoImpl teacherDao;
    private static CourseDaoImpl courseDao;
    private static ArchiveDaoImpl archiveDao;

    public CourseManager() throws DAOException {
        teacherDao = new TeacherDaoImpl();
        courseDao = new CourseDaoImpl();
        archiveDao = new ArchiveDaoImpl();
    }
    public void setGrade(Student student, Course course, int grade)
    {
        Record record = new Record(course, student, grade);
        Archive.getInstance().addRecord(record);
        //course.removeStudent(student);
    }
    public void sortCourses(List<Course> courses, String sortBy) {
        switch (sortBy) {
            case "courseName":
                Collections.sort(courses, Comparator.comparing(Course::getName));
                break;
            case "teacherName":
                Collections.sort(courses, Comparator.comparing(course -> course.getTeacher().getName()));
                break;
//            case "numStudents":
//                Collections.sort(courses, Comparator.comparingInt(course -> course.getStudents().size()));
//                break;
            default:
                throw new IllegalArgumentException("Invalid sort criteria.");
        }
    }
    public List<Course> findCoursesByTeacher(List<Course> courseList, Teacher teacher) {
        List<Course> matchingCourses = new ArrayList<>();

        for (Course course : courseList) {
            if (course.getTeacher().equals(teacher)) {
                matchingCourses.add(course);
            }
        }

        return matchingCourses;
    }

    public void printNumberOfStudentsCourse(String course_name)
    {
        System.out.println("Number of students of course " + course_name + ": "+courseDao.countStudentsOnCourse(course_name));
    }
    public void printAllCoursesInfo()
    {
        List<Course> courses =  courseDao.getAllCourses();
        for(Course course:courses){
            System.out.println(course.toString());
        }
    }

    public void printTeachersWithMultipleCourses()
    {
        List<Teacher> teachers = teacherDao.getMultipleCourseTeachers();
        System.out.println("Teachers with multiple courses are:");
        for(Teacher teacher:teachers){
            System.out.println(teacher.getName());
        }
    }

    public void printStudentsGrades(String students_name){
        Map<String,Integer> grades = archiveDao.getStudentGrades(students_name);
        System.out.println("Grades for student " + students_name + ":");
        for (Map.Entry<String, Integer> entry : grades.entrySet()) {
            String courseName = entry.getKey();
            int grade = entry.getValue();
            System.out.println("Course: " + courseName + ", Grade: " + grade);
        }
    }

    public void openNewCourse(String courseName,Integer teacher_id, Date startDate){
        courseDao.openNewCourse(courseName,teacher_id,startDate);
    }
}

import Database.*;
import DAO.*;
import managers.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws JDBCConnectionException, DAOException {

    CourseManager courseManager = new CourseManager();

    Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            printMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            String courseName,studentName;
            switch (choice) {
                case 1:
                    System.out.print("Enter the course name: ");
                    courseName = scanner.nextLine();
                    courseManager.printNumberOfStudentsCourse(courseName);
                    break;
                case 2:
                    courseManager.printTeachersWithMultipleCourses();
                    break;
                case 3:
                    System.out.print("Enter the student name: ");
                    studentName = scanner.nextLine();
                    courseManager.printStudentsGrades(studentName);
                    break;
                case 4:
                    System.out.print("Enter the course name: ");
                    courseName = scanner.nextLine();
                    System.out.print("Enter the teacher ID: ");
                    int teacherId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the start date (yyyy-MM-dd): ");
                    String startDateStr = scanner.nextLine();
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date startDate = dateFormat.parse(startDateStr);
                        courseManager.openNewCourse(courseName,teacherId,startDate);

                        System.out.println("New course opened successfully.");
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please try again.");
                    }
                    break;
                case 5:
                    courseManager.printAllCoursesInfo();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);


//        try{
//            String url = "jdbc:mysql://localhost/store";
//            String username = "root";
//            String password = "1234";
//            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
//            try (Connection conn = DriverManager.getConnection(url, username, password)){
//
//                System.out.println("Connection to Store DB succesfull!");
//            }
//        }
//        catch(Exception ex){
//            System.out.println("Connection failed...");
//
//            System.out.println(ex);
//        }


//        List<Student> students = new ArrayList<>();
//
//        Student student1 = new Student("Ivan");
//        students.add(student1);
//        Student student2 = new Student("Sergey");
//        students.add(student2);
//        Student student3 = new Student("Alexey");
//        students.add(student3);
//        Student student4 = new Student("Katya");
//        students.add(student4);
//        Student student5 = new Student("Andrey");
//        students.add(student5);
//        Student student6 = new Student("Olga");
//        students.add(student6);
//        Student student7 = new Student("Dmitriy");
//        students.add(student7);
//
//        Teacher teacher1 = new Teacher("Ivanov");
//        Teacher teacher2 = new Teacher("Petrov");
//
//        List<Course> courses = new ArrayList<>();
//        Course MathCourse = CourseFactory.getClassFromFactory("math",teacher1);
//        Course EnglishCourse = CourseFactory.getClassFromFactory("english",teacher2);
//        Course HistoryCourse = CourseFactory.getClassFromFactory("history",teacher1);
//
//        MathCourse.addStudent(student1);
//        MathCourse.addStudent(student2);
//        MathCourse.addStudent(student3);
//        MathCourse.addStudent(student4);
//        EnglishCourse.addStudent(student5);
//        EnglishCourse.addStudent(student6);
//        HistoryCourse.addStudent(student7);
//
//        courses.add(MathCourse);
//        courses.add(EnglishCourse);
//        courses.add(HistoryCourse);
//
//        System.out.println("Before sorting:");
//        CourseManager.printCoursesInfo(courses);
//
//        CourseManager.sortCourses(courses,"courseName");
//
//        System.out.println("\nafter sorting by course name:");
//        CourseManager.printCoursesInfo(courses);
//
//        CourseManager.sortCourses(courses,"numStudents");
//        System.out.println("\nafter sorting by number of students:");
//        CourseManager.printCoursesInfo(courses);
//
//        System.out.println("\nCourses where teacher is " + teacher1.getName());
//        CourseManager.printCoursesInfo(CourseManager.findCoursesByTeacher(courses,teacher1));
    }

    private static void printMenu() {
        System.out.println("\nThe options are:");
        System.out.println("1. Count students on a course");
        System.out.println("2. List teachers with multiple courses");
        System.out.println("3. Get student grades");
        System.out.println("4. Open a new course");
        System.out.println("5. List all courses");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }
}
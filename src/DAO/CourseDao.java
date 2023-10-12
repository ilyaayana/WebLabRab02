package DAO;

import entities.Course;

import java.util.Date;
import java.util.List;

public interface CourseDao {
    public int countStudentsOnCourse(String course_name);
    List<Course> getAllCourses();
    void openNewCourse(String courseName,Integer teacher_id, Date startDate);
}

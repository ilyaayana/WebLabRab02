package DAO;

import Database.JDBCConnectionException;
import entities.Course;
import entities.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class CourseDaoImpl extends DAO implements CourseDao {

    private static final String SQL_SELECT_ALL = "SELECT * FROM courses";
    private static final String SQL_INSERT = "INSERT INTO courses (course_name, teacher_id, start_date) VALUES (?, ?, ?)";
    private static final String SQL_COUNT_STUDENTS_ON_COURSE = "SELECT COUNT(*) AS student_count\n" +
            "FROM StudentsCourses\n" +
            "INNER JOIN Courses ON StudentsCourses.course_id = Courses.course_id\n" +
            "WHERE Courses.course_name = ?";

    public CourseDaoImpl() throws DAOException {
        super();
    }

    @Override
    public int countStudentsOnCourse(String course_name) {
        int count = 0;
        try {
            Connection conn = cnr.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_COUNT_STUDENTS_ON_COURSE);
            statement.setString(1, course_name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (JDBCConnectionException | SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        try {
            Connection conn = cnr.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                TeacherDaoImpl TeacherDao = new TeacherDaoImpl();
                Teacher teacher = TeacherDao.getTeacherById(resultSet.getInt("teacher_id"));
                Date start_date = resultSet.getDate("start_date");
                Date end_date = resultSet.getDate("end_date");
                Course course = new Course(courseName,teacher,start_date,end_date);
                courses.add(course);
            }
        } catch (JDBCConnectionException | SQLException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }

    @Override
    public void openNewCourse(String courseName, Integer teacher_id, Date startDate) {
        try {
            Connection conn = cnr.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_INSERT);
            statement.setString(1, courseName);
            statement.setInt(2,teacher_id);
            statement.setDate(3, new java.sql.Date(startDate.getTime()));
            statement.executeUpdate();
        } catch (JDBCConnectionException | SQLException e) {
            e.printStackTrace();
        }
    }
}

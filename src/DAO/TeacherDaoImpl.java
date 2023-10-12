package DAO;

import Database.JDBCConnectionException;
import entities.Course;
import entities.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl extends DAO implements TeacherDao {

    private static final String SQL_SELECT_ALL = "SELECT * FROM teachers";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM teachers WHERE teacher_id=?";
    private static final String SQL_SELECT_MULTIPLE_COURSE_TEACHERS_NAMES = "SELECT teacher_name FROM Teachers " +
            "INNER JOIN Courses ON Teachers.teacher_id = Courses.teacher_id " +
            "GROUP BY teacher_name HAVING COUNT(*) > 1";
    public TeacherDaoImpl() throws DAOException {
        super();
    }

    @Override
    public Teacher getTeacherById(int teacherId) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Teacher teacher = null;
        try {
            conn = cnr.getConnection();
            statement = conn.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, teacherId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String teacherName = resultSet.getString("teacher_name");
                teacher = new Teacher(teacherName);
            }
            conn.close();
        } catch (SQLException | JDBCConnectionException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Teacher> teachers = new ArrayList<>();
        try {
            conn = cnr.getConnection();
            statement = conn.prepareStatement(SQL_SELECT_ALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Teacher teacher = new Teacher(resultSet.getString("teacher_name"));
                teachers.add(teacher);
            }
        } catch (SQLException | JDBCConnectionException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    @Override
    public List<Teacher> getMultipleCourseTeachers() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Teacher> teachers = new ArrayList<>();
        try {
            conn = cnr.getConnection();
            statement = conn.prepareStatement(SQL_SELECT_MULTIPLE_COURSE_TEACHERS_NAMES);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Teacher teacher = new Teacher(resultSet.getString("teacher_name"));
                teachers.add(teacher);
            }
        } catch (SQLException | JDBCConnectionException e) {
            e.printStackTrace();
        }
        return teachers;
    }
}

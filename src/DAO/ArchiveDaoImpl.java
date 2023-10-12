package DAO;
import Database.JDBCConnectionException;
import entities.Record;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArchiveDaoImpl extends DAO implements ArchiveDao {

    private static final String SQL_SELECT_BY_NAME = "SELECT Courses.course_name, GradesArchive.grade FROM GradesArchive " +
            "INNER JOIN Students ON GradesArchive.student_id = Students.student_id " +
            "INNER JOIN Courses ON GradesArchive.course_id = Courses.course_id " +
            "WHERE Students.student_name = ?";

    public ArchiveDaoImpl() throws DAOException {
        super();
    }

    @Override
    public Map<String, Integer> getStudentGrades(String studentName) {
        Map<String, Integer> gradesMap = new HashMap<>();
        try {
            Connection conn = cnr.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_NAME);
            statement.setString(1, studentName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                int grade = resultSet.getInt("grade");
                gradesMap.put(courseName, grade);
            }
        } catch (JDBCConnectionException | SQLException e) {
            e.printStackTrace();
        }
        return gradesMap;
    }
}

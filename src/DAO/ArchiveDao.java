package DAO;

import java.util.Map;

public interface ArchiveDao {
    Map<String, Integer> getStudentGrades(String studentName);
}
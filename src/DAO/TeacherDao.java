package DAO;

import entities.Teacher;

import java.util.List;

public interface TeacherDao {
    Teacher getTeacherById(int teacherId);
    List<Teacher> getAllTeachers();
    List<Teacher> getMultipleCourseTeachers();
}

package entities;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Course {
    private String name;
    private Teacher teacher;
    private Date start_date;
    private Date end_date;
    public Course(String name) {
        this.name = name;
    }
    public Course(String name, Teacher teacher) {
        this(name); // Вызывает конструктор Course(String name) первым
        this.teacher = teacher;
    }
    public Course(String name, Date start_date, Date end_date) {
        this(name);
        this.start_date = start_date;
        this.end_date = end_date;
    }
    public Course(String name, Teacher teacher, Date start_date, Date end_date) {
        this(name, teacher);
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String getName() {
        return name;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public Date getStart_date() {
        return start_date;
    }
    public Date getEnd_date() {
        return end_date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Course Name: ").append(name).append("\n");
        sb.append("Teacher: ").append(teacher.getName()).append("\n");
        sb.append("Start Date: ").append(start_date).append("\n");
        sb.append("End Date: ").append(end_date).append("\n");
        return sb.toString();
    }

}

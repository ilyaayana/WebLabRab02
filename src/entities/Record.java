package entities;

public class Record {
    private Course course;
    private Student student;
    private int grade;

    public Record(Course course, Student student, int grade) {
        this.course = course;
        this.student = student;
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Course: " + course.getName() + ",Student: " + student.getName() + ", Grade: " + grade;
    }
}

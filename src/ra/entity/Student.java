package ra.entity;

public class Student {
    private int StudentId;
    private String StudentName;
    private int StudentAge;
    private boolean Status;

    public Student() {
    }

    public Student(int studentId, String studentName, int studentAge, boolean status) {
        StudentId = studentId;
        StudentName = studentName;
        StudentAge = studentAge;
        Status = status;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public int getStudentAge() {
        return StudentAge;
    }

    public void setStudentAge(int studentAge) {
        StudentAge = studentAge;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String toString() {
        return "Mã SV: " + this.StudentId +
                " - Tên SV: " + this.StudentName +
                " - Tuổi: " + this.StudentAge +
                " - Trạng thái: " + (this.Status?"Hoạt động":"Không hoạt động");
    }
}

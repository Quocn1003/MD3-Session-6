package ra.business;

import ra.entity.Student;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class StudentBusiness {
    //hiển thị danh sách sinh viên
    public static List<Student> findAll() {
        //Khai báo đối tượng Connection và CallableStament
        Connection con = null;
        CallableStatement callSt = null;
        List<Student> listStudents = null;
        try {
            //Khởi tạo đối tượng Connection
            con = ConnectionDB.openconnection();
            //Khởi toạ đối tượng CallableStament từ đối tượng Connection
            callSt = con.prepareCall("{call getAllStudents()}");
            //Thực hiện gọi proc và trả kết quả
            ResultSet rs = callSt.executeQuery();
            //Duyệt rs và lấy dữ liệu đẩy vào listStudents
            listStudents = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setStudentName(rs.getString("student_name"));
                student.setStudentAge(rs.getInt("student_age"));
                student.setStatus(rs.getBoolean("student_status"));
                listStudents.add(student);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        } finally {
            //Đóng kết nối và callst
            ConnectionDB.closeconnection(con, callSt);
        }
        return listStudents;
    }

    //thêm mới sinh viên
    public static boolean save(Student student) {
        Connection con = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
                con = ConnectionDB.openconnection();
                callSt = con.prepareCall("{call add_student(?,?,?)}");
                //Set giá trị cho các tham số vào
                callSt.setString(1, student.getStudentName());
                callSt.setInt(2, student.getStudentAge());
                callSt.setBoolean(3,student.isStatus());
                //Đăng ký dữ liệu cho các tham số trả ra
                //Thực hiện goi prc
                callSt.executeUpdate();
                result = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeconnection(con, callSt);
        }
        return result;
    }

    //hiển thị sinh viên theo id
    public static Student findById(int studentId) {
        //Khai báo đối tượng Connection và CallableStament
        Connection con = null;
        CallableStatement callSt = null;
        List<Student> listStudents = null;
        Student student = null;
        try {
            //Khởi tạo đối tượng Connection
            con = ConnectionDB.openconnection();
            //Khởi toạ đối tượng CallableStament từ đối tượng Connection
            callSt = con.prepareCall("{call get_student_by_id(?)}");
            callSt.setInt(1, studentId);
            //Thực hiện gọi proc và trả kết quả
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setStudentName(rs.getString("student_name"));
                student.setStudentAge(rs.getInt("student_age"));
                student.setStatus(rs.getBoolean("student_status"));
                listStudents.add(student);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        } finally {
            //Đóng kết nối và callst
            ConnectionDB.closeconnection(con, callSt);
        }
        return student;
    }

    //Cập nhật sinh viên
    public static boolean update(Student student) {
        Connection con = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            con = ConnectionDB.openconnection();
            callSt = con.prepareCall("{call update_studen(?,?,?,?)}");
            //Set giá trị cho các tham số vào
            callSt.setInt(1, student.getStudentId());
            callSt.setString(2, student.getStudentName());
            callSt.setInt(3, student.getStudentAge());
            callSt.setBoolean(4,student.isStatus());
            //Đăng ký dữ liệu cho các tham số trả ra
            //Thực hiện goi prc
            callSt.executeUpdate();
            result = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeconnection(con, callSt);
        }
        return result;
    }

    //Xoá sinh viên
    public static boolean delete(int studentId) {
        Connection con = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            con = ConnectionDB.openconnection();
            callSt = con.prepareCall("{call delete_student(?)}");
            //Set giá trị cho các tham số vào
            callSt.setInt(1, studentId);
            //Đăng ký dữ liệu cho các tham số trả ra
            //Thực hiện goi prc
            callSt.executeUpdate();
            result = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeconnection(con, callSt);
        }
        return result;
    }

    //Thống kê sinh viên
    public static int getCntStudent(){
        Connection con = null;
        CallableStatement callSt = null;
        int count_students = 0;
        try {
            con = ConnectionDB.openconnection();
            callSt = con.prepareCall("{call get_cnt_students()}");
            //Đăng ký dữ liệu đầu ra
            callSt.registerOutParameter(1, Types.INTEGER);
            //Thực hiện gọi proc
            callSt.execute();
            //Lấy giá trị các tham số trả ra
            count_students = callSt.getInt(1);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeconnection(con, callSt);
        }
        return count_students;
    }
}

package ra.run;

import ra.business.StudentBusiness;
import ra.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("**********MENU**********");
            System.out.println("1. Danh sách sinh viên.");
            System.out.println("2. Thêm sinh viên.");
            System.out.println("3. Cập nhật sinh viên.");
            System.out.println("4. Xoá sinh viên.");
            System.out.println("5. Thống kê số lượng sinh viên.");
            System.out.println("6. Thoát.");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    List<Student> listStudents = StudentBusiness.findAll();
                    listStudents.stream().forEach(System.out::println);
                    break;
                case 2:
                    Student student = new Student();
                    System.out.print("Nhập vào tên sinh viên: ");
                    student.setStudentName(sc.nextLine());
                    System.out.print("Nhập vào tuổi sinh viên: ");
                    student.setStudentAge(Integer.parseInt(sc.nextLine()));
                    System.out.print("Nhập vào trạng thái sinh viên: ");
                    student.setStatus(Boolean.parseBoolean(sc.nextLine()));
                    boolean result = StudentBusiness.save(student);
                    if (result) {
                        System.out.println("Thêm mới thành công!");
                    }else {
                        System.err.println("Có lỗi trong quá trình thêm mới!");
                    }
                    break;
                case 3:
                    System.out.print("Nhập vào mã sinh viên: ");
                    int id = Integer.parseInt(sc.nextLine());
                    Student studentUpdate = StudentBusiness.findById(id);
                    if (studentUpdate != null) {
                        System.out.println("CẬP NHẬT SINH VIÊN: ");
                        System.out.print("Nhập vào tên sinh viên: ");
                        studentUpdate.setStudentName(sc.nextLine());
                        System.out.print("Nhập vào tuổi sinh viên: ");
                        studentUpdate.setStudentAge(Integer.parseInt(sc.nextLine()));
                        System.out.print("Nhập vào trạng thái sinh viên: ");
                        studentUpdate.setStatus(Boolean.parseBoolean(sc.nextLine()));
                        //thực hiện cập nhật
                        boolean resultUpdate = StudentBusiness.update(studentUpdate);
                        if (resultUpdate) {
                            System.out.println("Cập nhật thành công!");
                        }else {
                            System.err.println("Có lỗi trong quá trình cập nhật!");
                        }
                    }else{
                        System.err.println("Sinh viên không tồn tại!");
                    }
                    break;
                case 4:
                    System.out.print("Nhập vào mã sinh viên: ");
                    id = Integer.parseInt(sc.nextLine());
                    Student studentDelete = StudentBusiness.findById(id);
                    if (studentDelete != null) {
                        boolean resultDelete = StudentBusiness.delete(id);
                        if (resultDelete) {
                            System.out.println("Xoá sinh viên thành công");
                        }else {
                            System.err.println("Có lỗi trong quá trình xoá!");
                        }
                    }else {
                        System.err.println("Sinh viên không tồn tại!");
                    }
                    break;
                case 5:
                    System.out.println("THỐNG KÊ SỐ LƯỢNG SINH VIÊN: ");
                    int studentCount = StudentBusiness.getCntStudent();
                    System.out.println("Số lượng sinh viên: "+ studentCount );
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1 đến 6.");
            }

        }while(true);
    }
}

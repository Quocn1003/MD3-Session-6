package ra.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/studentmanagement";
    private static final String USER_NAME= "root";
    private static final String PASSWORD = "Nam123123@";
    // Phương thức khởi tạo connection để làm việc với database MySQL
    public static Connection openconnection (){
        Connection con = null;
        try{
            //1.set Driver cho DriverManager
            Class.forName(DRIVER);
            //2.Khởi tạo đối tượng Connection từ DriverManager
            con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        }catch ( Exception e ){
            e.printStackTrace();
        }
        return con;
    }

    //Phương thức đóng Connection, callableStament
    public static void closeconnection(Connection con, CallableStatement callSt){
        if(con != null){
            try {
                con.close();
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(callSt != null){
            try {
                callSt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Connection con = openconnection();
        if(con != null){
            System.out.println("Kết nối thành công");
        }else {
            System.err.println("Kết nối thất bại!");
        }
    }


}

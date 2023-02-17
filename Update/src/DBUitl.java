import java.sql.*;

public class DBUitl {
    //工具类中的方法都是静态私有的，直接通过类调用就行
    private DBUitl(){}
    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    //获取对象
    public static Connection getConnertion() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","123456");
    }
    //关闭资源
    public static void close(Connection conn , Statement stmt , ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

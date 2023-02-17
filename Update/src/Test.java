import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Test {
    public static void main(String[] args) {
        //初始化界面
        Map<String,String> userLoginInto = initUI();
        //验证用户名和密码
        boolean loginSuccess = login(userLoginInto);
        System.out.println(loginSuccess ? "登陆成功":"登陆失败");
    }
    private static boolean login(Map<String, String> userLoginInto) {
        String loginName = userLoginInto.get("loginName");
        String loginPwd  = userLoginInto.get("loginPwd");
        boolean loginSuccess = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zbx","root","123456");
            String sql = "select * from t_user where username = ? and password = ? ";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,loginName);
            stmt.setString(2,loginPwd);
            rs = stmt.executeQuery();
            if(rs.next()){
                loginSuccess = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs != null){
                try{
                    rs.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(stmt != null){
                try{
                    stmt.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return loginSuccess;
    }
    private static Map<String, String> initUI() {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入用户名:");
        String loginName = sc.nextLine();
        System.out.print("请输入密码：");
        String loginPwd = sc.nextLine();
        Map<String,String> userLoginInto = new HashMap<>();
        userLoginInto.put("loginName",loginName);
        userLoginInto.put("loginPwd",loginPwd);
        return userLoginInto;
    }
}
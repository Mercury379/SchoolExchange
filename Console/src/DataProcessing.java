import java.sql.*;
import java.util.Enumeration;
import java.util.Vector;

public class DataProcessing {
    private static boolean connectToDB = false;
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    public static boolean connectToDatabase(){
        String driverName="com.mysql.cj.jdbc.Driver"; // 加载数据库驱动类
        String url="jdbc:mysql://localhost:3306/users?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false"; // 声明数据库的URL
        String user="root"; // 数据库用户
        String password="HEQuan20031108";
        try{
            Class.forName(driverName);
            connection= DriverManager.getConnection(url,user,password);
            connectToDB=true;
        }catch (ClassNotFoundException e){
            System.out.println("数据库驱动类名错误！");
        }catch (SQLException e){
            System.out.println("数据库连接错误！");
        }
        return connectToDB;
    }
    public static boolean certificateStudent(String studentid,String institute,String speciality,String sclass,String name) throws SQLException {
        boolean isStudent=false;
        if (!connectToDB) {
            System.out.println("数据库连接错误！");
        }else{
            statement=connection.createStatement();
            String sql="select * from students";
            resultSet=statement.executeQuery(sql);
            while(resultSet.next()) {
                String Studentid = resultSet.getString("studentid");
                String Institue=resultSet.getString("institute");
                String Speciality=resultSet.getString("speciality");
                String Sclass=resultSet.getString("class");
                String Name=resultSet.getString("name");
                if (Studentid.equals(studentid) && Institue.equals(institute) && Speciality.equals(speciality) && Sclass.equals(sclass) && Name.equals(name)) {
                    isStudent= true;
                }
            }
        }
        return isStudent;
    }
    public static AbstractUser searchUser(String id,String password) throws SQLException {
        if (!connectToDB) {
            System.out.println("数据库连接错误！");
            return null;
        }else{
            statement=connection.createStatement();
            String sql="select * from login";
            resultSet=statement.executeQuery(sql);
            AbstractUser user;
            while(resultSet.next()) {
                String Id = resultSet.getString("id");
                String Password=resultSet.getString("password");
                if (Id.equals(id) && Password.equals(password)) {
                    String role = resultSet.getString("isAdministrator");
                    if (role.equals("0")) {
                        user=new StudentUser(id,password,"studentuser");
                    }else{
                        user=new Administrator(id,password,"administrator");
                    }
                    return user;
                }
            }
            return null;
        }
    }
    public static boolean certificateStudentid(String id) {
        if (!connectToDB) {
            System.out.println("数据库连接错误！");
            return false;
        }else{
            try{
                statement=connection.createStatement();
                String sql="select * from students";
                resultSet=statement.executeQuery(sql);
                while(resultSet.next()) {
                    String Id = resultSet.getString("studentid");
                    if (Id.equals(id)) {
                        return true;
                    }
                }
                return false;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }
    public static boolean insertUser(String id,String password,String role){
        if(!connectToDB){
            throw new IllegalStateException("数据库未连接！");
        }else{
            String sql="INSERT INTO login (id,password,isAdministrator) VALUES (?,?,?)";
            try {
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,id);
                preparedStatement.setString(2,password);
                preparedStatement.setString(3,role);
                int temp=preparedStatement.executeUpdate();
                preparedStatement.close();
                if(temp!=0)
                    return true;
                else{
                    return false;
                }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
            }
        }
    }
    public static boolean insertInfo(String id,String nickname,String age,String sex,String address,String phonenumber){
        if(!connectToDB){
            throw new IllegalStateException("数据库未连接！");
        }else{
            String sql="INSERT INTO information (id,nickname,age,sex,address,phonenumber) VALUES (?,?,?,?,?,?)";
            try {
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,id);
                preparedStatement.setString(2,nickname);
                preparedStatement.setString(3,age);
                preparedStatement.setString(4,sex);
                preparedStatement.setString(5,address);
                preparedStatement.setString(6,phonenumber);
                int temp=preparedStatement.executeUpdate();
                preparedStatement.close();
                if(temp!=0)
                    return true;
                else{
                    return false;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static boolean deleteUser(String id) {
        if(!connectToDB){
            throw new IllegalStateException("数据库未连接！");
        }else{
            try {
                statement=connection.createStatement();
                String sql="delete from login where id='"+id+"'";
                String sql2="delete from information where id='"+id+"'";
                if(statement.executeUpdate(sql)==0 || statement.executeUpdate(sql2)==0){
                    return false;
                }else{
                    return true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static boolean updatePassword(String id) {
        if(!connectToDB){
            throw new IllegalStateException("数据库未连接！");
        }else{
            String sql="UPDATE login SET password='"+id+"'WHERE id='"+id+"'";
            try {
                statement=connection.createStatement();
                if(statement.executeUpdate(sql)!=0){
                    return true;
                }else{
                    return false;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static boolean changePassword(String id,String password) {
        if(!connectToDB){
            throw new IllegalStateException("数据库未连接！");
        }else{
            String sql="UPDATE login SET password='"+password+"'WHERE id='"+id+"'";
            try {
                statement=connection.createStatement();
                if(statement.executeUpdate(sql)!=0){
                    return true;
                }else{
                    return false;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static boolean updateRole(String id) {
        if(!connectToDB){
            throw new IllegalStateException("数据库未连接！");
        }else{
            String sql="UPDATE login SET isAdministrator='"+"1"+"'WHERE id='"+id+"' and isAdministrator = '0'";
            try {
                statement=connection.createStatement();
                if(statement.executeUpdate(sql)!=0){
                    return true;
                }else{
                    return false;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static ResultSet listUserinfo() {
        try {
            if (!connectToDB) {
                throw new SQLException("数据库未连接！");
            }else{
                statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                String sql="select * from userinfo where role = '0'";
                resultSet=statement.executeQuery(sql);
                return resultSet;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean changeSelfinfo(String id,String nickname,String age,String sex,String address,String phonenumber) {
        if(!connectToDB){
            throw new IllegalStateException("数据库未连接！");
        }else{
            String sql="UPDATE information SET nickname='"+nickname+"',age='"+age+"',sex='"+sex+"',address='"+address+"',phonenumber='"+phonenumber+"' WHERE id='"+id+"'";
            try {
                statement=connection.createStatement();
                if(statement.executeUpdate(sql)!=0){
                    return true;
                }else{
                    return false;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

package GUI;

import javax.swing.*;
import java.sql.*;

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
                    break;
                }
            }
        }
        return isStudent;
    }
    public static AbstractUser searchUser(String id, String password) throws SQLException {
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
    public static ResultSet searchSelfInfo(String id) throws SQLException {
        if (!connectToDB) {
            System.out.println("数据库连接错误！");
            return null;
        }else{
            statement=connection.createStatement();
            String sql="select * from information where id='"+id+"'";
            resultSet=statement.executeQuery(sql);
            return resultSet;
        }
    }
    public static boolean searchIsUser(String id) throws SQLException {
        if (!connectToDB) {
            System.out.println("数据库连接错误！");
            return false;
        }else{
            statement=connection.createStatement();
            String sql="select * from login";
            resultSet=statement.executeQuery(sql);
            AbstractUser user;
            while(resultSet.next()) {
                String Id = resultSet.getString("id");
                if(Id.equals(id)){
                    return true;
                }
            }
        }
        return false;
    }
    public static ResultSet searchStudentUser(String id,String name,String institute,String speciality,String Class) throws SQLException {
        if (!connectToDB) {
            System.out.println("数据库连接错误！");
            return null;
        }else{
            statement=connection.createStatement();
            String sql;
            if(!id.equals("") && name.equals("") && institute.equals("") && speciality.equals("") && Class.equals("")){
                sql="select * from studentuser where id ='"+id+"'";
            }else if(id.equals("") && !name.equals("") && institute.equals("") && speciality.equals("")&& Class.equals("")){
                sql="select * from studentuser where name ='"+name+"'";
            }else if(id.equals("") && name.equals("") && !institute.equals("") && speciality.equals("")&& Class.equals("")){
                sql="select * from studentuser where institute ='"+institute+"'";
            }else if(id.equals("") && name.equals("") && institute.equals("") && !speciality.equals("")&& Class.equals("")){
                sql="select * from studentuser where speciality ='"+speciality+"'";
            } else if(id.equals("") && name.equals("") && institute.equals("") && speciality.equals("")&& !Class.equals("")){
            sql="select * from studentuser where class ='"+Class+"'";
            } else if(id.equals("") && name.equals("") && !institute.equals("") && !speciality.equals("")&& Class.equals("")){
                sql="select * from studentuser where speciality ='"+speciality+"' and institute = '"+institute+"'";
            } else if(id.equals("") && name.equals("") && !institute.equals("") && !speciality.equals("")&& !Class.equals("")){
                sql="select * from studentuser where speciality ='"+speciality+"' and institute = '"+institute+"' and class= '"+Class+"'";
            }else{
                sql = "select * from studentuser";
            }
            resultSet=statement.executeQuery(sql);
            return resultSet;
        }
    }
    public static ResultSet searchStudent(String id,String name,String institute,String speciality,String Class) throws SQLException {
        if (!connectToDB) {
            System.out.println("数据库连接错误！");
            return null;
        }else{
            statement=connection.createStatement();
            String sql;
            if(!id.equals("") && name.equals("") && institute.equals("") && speciality.equals("") && Class.equals("")){
                sql="select * from students where studentid ='"+id+"' and NOT EXISTS (SELECT * FROM login WHERE studentid=id)";
            }else if(id.equals("") && !name.equals("") && institute.equals("") && speciality.equals("")&& Class.equals("")){
                sql="select * from students where name ='"+name+"' and NOT EXISTS (SELECT * FROM login WHERE studentid=id)";
            }else if(id.equals("") && name.equals("") && !institute.equals("") && speciality.equals("")&& Class.equals("")){
                sql="select * from students where institute ='"+institute+"' and NOT EXISTS (SELECT * FROM login WHERE studentid=id)";
            }else if(id.equals("") && name.equals("") && institute.equals("") && !speciality.equals("")&& Class.equals("")){
                sql="select * from students where speciality ='"+speciality+"' and NOT EXISTS (SELECT * FROM login WHERE studentid=id)";
            } else if(id.equals("") && name.equals("") && institute.equals("") && speciality.equals("")&& !Class.equals("")){
                sql="select * from students where class ='"+Class+"' and NOT EXISTS (SELECT * FROM login WHERE studentid=id)";
            } else if(id.equals("") && name.equals("") && !institute.equals("") && !speciality.equals("")&& Class.equals("")){
                sql="select * from students where speciality ='"+speciality+"' and institute = '"+institute+"' and NOT EXISTS (SELECT * FROM login WHERE studentid=id)";
            } else if(id.equals("") && name.equals("") && !institute.equals("") && !speciality.equals("")&& !Class.equals("")){
                sql="select * from students where speciality ='"+speciality+"' and institute = '"+institute+"' and class= '"+Class+"' and NOT EXISTS (SELECT * FROM login WHERE studentid=id)";
            }else{
                sql = "select * from students where NOT EXISTS (SELECT * FROM login WHERE studentid=id)";
            }
            resultSet=statement.executeQuery(sql);
            return resultSet;
        }
    }
    public static boolean certificateStudentid(String id) {
        if (!connectToDB) {
            System.out.println("数据库连接错误！");
            return false;
        }else{
            try{
                statement=connection.createStatement();
                String sql="select * from login";
                resultSet=statement.executeQuery(sql);
                while(resultSet.next()) {
                    String Id = resultSet.getString("id");
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
    public static boolean certificateStudentEnable(String id) {
        if (!connectToDB) {
            System.out.println("数据库连接错误！");
            return false;
        }else{
            try{
                statement=connection.createStatement();
                String sql="select * from login";
                resultSet=statement.executeQuery(sql);
                while(resultSet.next()) {
                    String Id = resultSet.getString("id");
                    if (Id.equals(id)&&resultSet.getString("isEnable").equals("0")) {
                        return true;
                    }
                }
                return false;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }
    public static boolean insertUser(String id,String password,String role,String enable){
        if(!connectToDB){
            throw new IllegalStateException("数据库未连接！");
        }else{
            String sql="INSERT INTO login (id,password,isAdministrator,isEnable,errorCount) VALUES (?,?,?,?,?)";
            try {
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,id);
                preparedStatement.setString(2,password);
                preparedStatement.setString(3,role);
                preparedStatement.setString(4,enable);
                preparedStatement.setString(5,"0");
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
    public static boolean insertOperation(String administratorid,String userid,String Operation){
        if(!connectToDB){
            throw new IllegalStateException("数据库未连接！");
        }else{
            String sql="INSERT INTO operation (AdministratorId,UserId,Time,Operation) VALUES (?,?,?,?)";
            try {
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,administratorid);
                preparedStatement.setString(2,userid);
                preparedStatement.setTimestamp(3,new Timestamp(System.currentTimeMillis()));
                preparedStatement.setString(4,Operation);
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
    public static ResultSet deleteStudentUser(String id) {
        if(!connectToDB){
            throw new IllegalStateException("数据库未连接！");
        }else{
            try {
                statement=connection.createStatement();
                String sql="delete from login where id='"+id+"'";
                String sql2="delete from information where id='"+id+"'";
                statement.executeUpdate(sql);
                statement.executeUpdate(sql2);
                String sql3="select * from studentuser";
                ResultSet resultSet=statement.executeQuery(sql3);
                return resultSet;
//                if(statement.executeUpdate(sql)==0 || statement.executeUpdate(sql2)==0){
//                    return false;
//                }else{
//                    return true;
//                }
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
    public static boolean zeroErrorCount() {
        if(!connectToDB){
            throw new IllegalStateException("数据库未连接！");
        }else{
            String sql="UPDATE login SET errorCount=0";
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
    public static int errorCountPlus(String id) {
        if(!connectToDB){
            throw new IllegalStateException("数据库未连接！");
        }else{
            try {
            String sql1="select * from login where id='"+id+"'";
            statement=connection.createStatement();
            ResultSet resultSet1=statement.executeQuery(sql1);
            resultSet1.next();
            int errorCount=resultSet1.getInt("errorCount");
            errorCount++;
            String sql2="UPDATE login SET errorCount="+errorCount+" WHERE id ='"+id+"'";
            statement=connection.createStatement();
            statement.executeUpdate(sql2);
            return errorCount;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static boolean zeroErrorCount(String id) {
        if(!connectToDB){
            throw new IllegalStateException("数据库未连接！");
        }else{
            String sql="UPDATE login SET errorCount=0 WHERE id='"+id+"'";
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
    public static  int searchErrorCount(String id){
        if(!connectToDB){
            throw new IllegalStateException("数据库未连接！");
        }else{
            try {
                String sql1="select * from login where id='"+id+"'";
                statement=connection.createStatement();
                ResultSet resultSet1=statement.executeQuery(sql1);
                resultSet1.next();
                int errorCount=resultSet1.getInt("errorCount");
                return errorCount;
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
    public static boolean updateEnable(String id,String status) {
        if(!connectToDB){
            throw new IllegalStateException("数据库未连接！");
        }else{
            String sql="UPDATE login SET isEnable='"+status+"'WHERE id='"+id+"'";
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
    public static ResultSet listUser() {
        try {
            if (!connectToDB) {
                throw new SQLException("数据库未连接！");
            }else{
                statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                String sql="select * from studentuser";
                resultSet=statement.executeQuery(sql);
                return resultSet;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ResultSet listStudent() {
        try {
            if (!connectToDB) {
                throw new SQLException("数据库未连接！");
            }else{
                statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                String sql="SELECT * FROM students WHERE NOT EXISTS (SELECT * FROM login WHERE studentid=id) ";
                resultSet=statement.executeQuery(sql);
                return resultSet;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ResultSet listOperation(String administratorId) {
        try {
            if (!connectToDB) {
                throw new SQLException("数据库未连接！");
            }else{
                statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                String sql="SELECT * FROM useroperation where AdministratorId='"+administratorId+"'";
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;

public class Administrator extends AbstractUser{
    public Administrator(String id, String password, String role) {
        super(id,password,role);
    }
    public void showMenu() {

        Scanner scan=new Scanner(System.in);

        String id;
        String password;
        String role;

        int flag=1;
        while(flag==1) {
            System.out.println("*****欢迎进入管理员菜单*****");
            System.out.println("    1、新增用户");
            System.out.println("    2、删除用户");
            System.out.println("    3、重置密码");
            System.out.println("    4、用户列表");
            System.out.println("    5、用户授权");
            System.out.println("    6、退出");
            System.out.println("*************************");
            System.out.println("请选择菜单：");      //administrator菜单
            int order = scan.nextInt();
            switch (order) {
                case 1:
                    System.out.println("请输入学号：");
                    id=scan.next();
                    if(DataProcessing.certificateStudentid(id)){
                        System.out.println("请输入密码：");
                        password=scan.next();
                        System.out.println("是否设置为管理员(1-是|0-否)：");
                        role=scan.next();
                        if(DataProcessing.insertUser(id,password,role)){   //判断新增用户是否已在哈希表中
                            DataProcessing.insertInfo(id,null,null,null,"null","null");
                            System.out.println("新增用户成功！");
                        }else{
                            System.out.println("新增用户失败！");
                        }
                    }else{
                        System.out.println("学号输入错误！");
                    }
                    break;
                case 2:
                    System.out.println("请输入学号：");
                    id=scan.next();
                    if(DataProcessing.deleteUser(id)) {    //判断删除用户是否在哈希表中
                        System.out.println("删除用户成功！");
                    }else{
                        System.out.println("删除用户失败！");
                    }
                    break;
                case 3:
                    System.out.println("请输入学号：");
                    id=scan.next();
                    if(DataProcessing.updatePassword(id)){   //判断修改用户是否在哈希表中
                        System.out.println("重置密码成功！");
                    }else{
                        System.out.println("重置失败！");
                    }
                    break;
                case 4:
                    ResultSet resultSet=DataProcessing.listUserinfo();
                    System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n","学号","密码","姓名","昵称","年龄","性别","地址","电话号码");
                    while(true){
                        try {
                            if (!resultSet.next()) break;
                            id=resultSet.getString("id");
                            password=resultSet.getString("password");
                            String name=resultSet.getString("name");
                            String nickname=resultSet.getString("nickname");
                            String age=resultSet.getString("age");
                            String sex=resultSet.getString("sex");
                            String address=resultSet.getString("address");
                            String phonenumber=resultSet.getString("phonenumber");
                            System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",id,password,name,nickname,age,sex,address,phonenumber);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 5:
                    System.out.println("请输入学号：");
                    id=scan.next();
                    if(DataProcessing.updateRole(id)){   //判断修改用户是否在哈希表中
                        System.out.println("授权管理员成功！");
                    }else{
                        System.out.println("授权管理员失败！");
                    }
                    break;
                case 6:
                    flag=0;
                    System.out.println("系统退出, 谢谢使用 ! ");
                    break;
                default:
                    System.out.println("命令不在菜单内！请重新输入！");
                    break;
            }
        }
    }
}

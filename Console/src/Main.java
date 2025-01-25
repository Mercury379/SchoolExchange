import java.sql.SQLException;
import java.util.*;
public class Main {
    public static void ShowMainMenu() {      //主函数菜单
        System.out.println("****欢迎进入校园二手交易平台****");
        System.out.println("        1.登录");
        System.out.println("        2.注册");
        System.out.println("        3.找回密码");
        System.out.println("        4.退出");
        System.out.println("****************************");
    }
    public static void main(String[] args) throws SQLException {
        DataProcessing.connectToDatabase();
        Scanner scan=new Scanner(System.in);
        while (true){
            ShowMainMenu();
            System.out.println("请选择菜单：");
            int command=scan.nextInt();
            if(command==1) {
                System.out.println("请输入学号：");
                String id = scan.next();
                System.out.println("请输入密码：");
                String password = scan.next();
                if (DataProcessing.searchUser(id, password) != null) {   //判断输入用户名和密码是否在数据库中
                    AbstractUser user = DataProcessing.searchUser(id,password);
                    user.showMenu();   //动态绑定
                }else{
                    System.out.println("输入错误！");
                }
            }else if(command==2){
                System.out.println("*******学生身份验证*********");
                System.out.println("请输入学号：");
                String id = scan.next();
                System.out.println("请输入学院：");
                String  institute = scan.next();
                System.out.println("请输入专业：");
                String  speciality = scan.next();
                System.out.println("请输入班级：");
                String  sclass = scan.next();
                System.out.println("请输入真实姓名：");
                String  name = scan.next();
                if(DataProcessing.certificateStudent(id,institute,speciality,sclass,name)){
                    System.out.println("*****学生身份验证成功！*****");
                    System.out.println("********密码设置**********");
                    System.out.println("请输入设置密码：");
                    String  newpassword = scan.next();
                    System.out.println("请再次输入密码：");
                    String  password = scan.next();
                    if (password.equals(newpassword)){
                        if(DataProcessing.insertUser(id,password,"0")){
                            System.out.println("********注册成功！********");
                            System.out.println("********信息填写**********");
                            System.out.println("请输入昵称：");
                            String nickname = scan.next();
                            System.out.println("请输入年龄：");
                            String  age = scan.next();
                            System.out.println("请输入性别：");
                            String  sex = scan.next();
                            System.out.println("请输入地址(必填)：");
                            String  address = scan.next();
                            System.out.println("请输入电话号码(必填)：");
                            String  phonenumber = scan.next();
                            DataProcessing.insertInfo(id,nickname,age,sex,address,phonenumber);
                        }else{
                            System.out.println("********注册失败！********");
                        }
                    }
                }else{
                    System.out.println("***学生身份验证失败！***");
                }
            } else if(command==3){
                System.out.println("*******学生身份验证*********");
                System.out.println("请输入学号：");
                String id = scan.next();
                System.out.println("请输入学院：");
                String  institute = scan.next();
                System.out.println("请输入专业：");
                String  speciality = scan.next();
                System.out.println("请输入班级：");
                String  sclass = scan.next();
                System.out.println("请输入真实姓名：");
                String  name = scan.next();
                if(DataProcessing.certificateStudent(id,institute,speciality,sclass,name)){
                    System.out.println("*****学生身份验证成功！*****");
                    System.out.println("********密码设置**********");
                    System.out.println("请输入设置密码：");
                    String  newpassword = scan.next();
                    System.out.println("请再次输入密码：");
                    String  password = scan.next();
                    if (password.equals(newpassword)){
                        DataProcessing.changePassword(id,password);
                    }else{
                        System.out.println("两次密码输入不一致！");
                    }
                }
            } else if (command==4) {
                System.out.println("系统退出, 谢谢使用 ! ");
                break;
            } else{
                System.out.println("命令不在菜单内！请重新输入！");
            }
        }
    }
}

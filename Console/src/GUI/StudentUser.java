package GUI;

import java.util.Scanner;

public class StudentUser extends AbstractUser {
    public StudentUser(String id, String password, String role) {
        super(id,password,role);
    }
    public void showMenu() {

        Scanner scan=new Scanner(System.in);


        int flag=1;
        while(flag==1) {
            System.out.println("******欢迎进入用户菜单******");
            System.out.println("    1、修改密码");
            System.out.println("    2、修改信息");
            System.out.println("    3、退出");
            System.out.println("*************************");
            System.out.println("请选择菜单：");      //administrator菜单
            int order = scan.nextInt();
            switch (order) {
                case 1:
                    System.out.println("请输入密码：");
                    setPassword(scan.next());
                    DataProcessing.changePassword(getId(),getPassword());
                    break;
                case 2:
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
                    if(DataProcessing.changeSelfinfo(getId(),nickname,age,sex,address,phonenumber)) {    //判断删除用户是否在哈希表中
                        System.out.println("修改信息成功！");
                    }else{
                        System.out.println("修改信息失败！");
                    }
                    break;
                case 3:
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

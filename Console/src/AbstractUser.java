public abstract class AbstractUser {
    private String id;  //管理员id为学工号，学生为学号用于登录
    private String password;
    private String role;
//    private String name;    //真实姓名
//    private String nickname; //昵称
//    private String age;
//    private String sex;
//    private String address;
//    private String phonenumber;
    AbstractUser(String id,String password,String role){
        this.id=id;
        this.password=password;
        this.role=role;
    }

    public void exitSystem() {
        System.out.println("系统退出, 谢谢使用 ! ");
        System.exit(0);
    }
    public abstract void showMenu();

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginFrame implements ActionListener{
    private JPanel panelLogin;
    private JButton buttonLogin;
    private JButton buttonRegister;
    private JTextField textFieldId;
    private JPasswordField passwordField;
    private JButton buttonSearchPassword;
    private JButton buttonExit;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel label1;
    private JLabel label2;
    private JLabel label0;
    private JPanel panel4;
    private JPanel panel5;
    private JButton helpButton;
    private JPanel panel6;
    private JFrame jFrame;
    private AbstractUser User;
    private int errorCount;
    JLabel label;


    public LoginFrame(JFrame jFrame) {
        this.jFrame=jFrame;
        //errorCount=0;
        jFrame.setTitle("登录页面");
        ImageIcon bg = new ImageIcon("D:/软件工程/Console/res/登录界面.jpg");	//创建一个背景图片
        label=new JLabel(bg);//把背景图片添加到标签里
        label.setBounds(0, 0, bg.getIconWidth()
                ,bg.getIconHeight());	//把标签设置为和图片等高等宽
        jFrame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        jFrame.getLayeredPane().setOpaque(false); //设置透明
        jFrame.setContentPane(panelLogin);
        panelLogin.setOpaque(false);
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);
        panel4.setOpaque(false);
        panel5.setOpaque(false);
        panel6.setOpaque(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocation(200,100);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
        buttonLogin.addActionListener(this);
        buttonExit.addActionListener(this);
        buttonRegister.addActionListener(this);
        buttonSearchPassword.addActionListener(this);
        helpButton.addActionListener(this);
////        ImageIcon bg = new ImageIcon("D:/软件工程/Console/src/登陆界面.jpg");	//创建一个背景图片
////        label=new JLabel();//把背景图片添加到标签里
////        label.setIcon(bg);
////        label.setBounds(0, 0, 500, 400);	//把标签设置为和图片等高等宽
////        jFrame.add(label,new Integer(Integer.MIN_VALUE));
//        //panelLogin.setOpaque(false);
//        //this.jFrame=jFrame;
//        jFrame.setContentPane(panelLogin);
//        //jFrame.add(panelLogin,new Integer(1));
//        new BackgroundImage(jFrame,jFrame.getContentPane(),"登陆界面.jpg");
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jFrame.setLocation(200,100);
//        jFrame.setResizable(false);
//        jFrame.pack();
//        jFrame.setVisible(true);
//        //new BackgroundImage(jFrame,jFrame.getContentPane(),"登陆页面.jpg");
//        buttonLogin.addActionListener(this);
//        buttonExit.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String id=textFieldId.getText();
            String password= passwordField.getText();
            if (e.getActionCommand().equals(buttonLogin.getText())) {
                if(!DataProcessing.certificateStudentEnable(id)){
                    JOptionPane.showMessageDialog(panelLogin,"输入用户名不存在！");
                    //JOptionPane.showMessageDialog(panelLogin,"该账号已冻结，请联系系统管理员进行解冻！");
                }else{
                    if (DataProcessing.searchUser(id, password) == null) {
                        if(DataProcessing.certificateStudentid(id)){
                            JOptionPane.showMessageDialog(panelLogin,"输入密码错误！");
                            DataProcessing.errorCountPlus(id);
                            //errorCount++;
                        }else{
                            JOptionPane.showMessageDialog(panelLogin,"输入用户名不存在！");
                        }
                        if(DataProcessing.searchErrorCount(id)==4){
                            JOptionPane.showMessageDialog(panelLogin,"请尝试找回密码,还有一次尝试的机会,否则该账号将被冻结！");
                        }
                        if(DataProcessing.searchErrorCount(id)==5){
                            JOptionPane.showMessageDialog(panelLogin,"该账号已冻结，请联系系统管理员进行解冻！");
                            DataProcessing.updateEnable(id,"1");
                        }
                    } else {
                        User=DataProcessing.searchUser(id,password);
                        EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    if(User.getRole().equals("administrator")){
                                        AdministratorFrame administratorFrame = new AdministratorFrame(new JFrame(), User);
                                        administratorFrame.setSuperjFrame(jFrame);
                                        DataProcessing.zeroErrorCount(User.getId());
                                    }else{
                                        StudentUserFrame studentUserFrame=new StudentUserFrame(new JFrame(),User);
                                        studentUserFrame.setSuperjFrame(jFrame);
                                    }
                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        jFrame.setVisible(false);
                    }
                }
            } else if(e.getActionCommand().equals(buttonRegister.getText())){
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            NewFrame newFrame=new NewFrame(new JFrame());
                            newFrame.setSuperjFrame(jFrame);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                jFrame.setVisible(false);
            }else if(e.getActionCommand().equals(buttonSearchPassword.getText())){
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            SearchPasswordFrame searchPasswordFrame=new SearchPasswordFrame(new JFrame());
                            searchPasswordFrame.setSuperjFrame(jFrame);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                jFrame.setVisible(false);
            }else if(e.getActionCommand().equals(helpButton.getText())){
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            HelpForm helpForm = new HelpForm(new JFrame());
                            helpForm.setSuperjFrame(jFrame);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                jFrame.setVisible(false);
            }else {
                Runtime.getRuntime().addShutdownHook(new Thread() {
                    public void run() {
                        //在此处添加退出应用程序前需完成工作，如：关闭网络连接、关闭数据库连接等
                        System.out.println("应用程序退出！");
                    }
                });
                System.exit(0);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

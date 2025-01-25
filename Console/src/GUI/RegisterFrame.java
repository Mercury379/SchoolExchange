package GUI;

import javax.swing.*;
import java.awt.event.*;

public class RegisterFrame {
    private JPasswordField password1;
    private JPasswordField password2;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel panelPassword;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JLabel label1;
    private JLabel label2;
    private JFrame jFrame;
    private JLabel label;
    private String Password1;
    private String Password2;
    private JFrame superjFrame;
    private String id;
    private boolean isPassword1;
    private boolean isPassword2;
    public JFrame loginjFrame;
    public void setLoginjFrame(JFrame jFrame){loginjFrame=jFrame;}

    public void setId(String id) {this.id = id;}

    public void setSuperjFrame(JFrame jFrame){
        superjFrame=jFrame;
    }
    RegisterFrame(JFrame jFrame){
        this.jFrame=jFrame;
        isPassword1=false;
        isPassword2=false;
        jFrame.setTitle("密码设置页面");
        ImageIcon bg = new ImageIcon("D:/软件工程/Console/res/密码设置.jpg");	//创建一个背景图片
        label=new JLabel(bg);//把背景图片添加到标签里
        label.setBounds(0, 0, bg.getIconWidth()
                ,bg.getIconHeight());	//把标签设置为和图片等高等宽
        jFrame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        jFrame.getLayeredPane().setOpaque(false); //设置透明
        jFrame.setContentPane(panelPassword);
        panelPassword.setOpaque(false);
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);
        panel4.setOpaque(false);
        panel5.setOpaque(false);
        jFrame.setLocation(200,100);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
        //jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                superjFrame.setVisible(true);
                //super.windowClosing(e);
            }
        });
        password1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                Password1=password1.getText();
                if(isFitPassword(Password1)){
                    label1.setText("√");
                    isPassword1=true;
                }else{
                    label1.setText("...");
                    isPassword1=false;
                }
            }
        });
        password2.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                Password1=password1.getText();
                Password2=password2.getText();
                if(isPassword(Password1,Password2)){
                    label2.setText("√");
                    isPassword2=true;
                }else{
                    label2.setText("...");
                    isPassword2=false;
                }
            }
        });
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isPassword1&&isPassword2) {
                    DataProcessing.insertUser(id, Password2,"0","0");
                    InfoFrame infoFrame=new InfoFrame(new JFrame());
                    infoFrame.setId(id);
                    infoFrame.setPassword(Password2);
                    infoFrame.setSuperjFrame(jFrame);
                    infoFrame.setLoginjFrame(loginjFrame);
                    jFrame.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(panelPassword, "请检查密码输入！");
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                superjFrame.setVisible(true);
            }
        });
    }
    public boolean isFitPassword(String password){
        boolean isFit=false;
        char[] temp=password.toCharArray();
        int length=temp.length;
        if(length<8||length>20){
            return isFit;
        }
        boolean minAlpha=false;
        boolean maxAlpha=false;
        boolean number=false;
        boolean other=false;
        for(int i=0;i<length;i++){
            if(temp[i]>='a'&&temp[i]<='z'){
                minAlpha=true;
            } else if(temp[i]>='A'&&temp[i]<='Z'){
                maxAlpha=true;
            } else if(temp[i]>='0'&&temp[i]<='9'){
                number=true;
            }else{
                other=true;
            }
            if(minAlpha&&maxAlpha&&number&&other){
                isFit=true;
                break;
            }
        }
        return isFit;
    }
    public boolean isPassword(String password1,String password2){
        if(password2.equals(password1)){
            return true;
        }else{
            return false;
        }
    }
}

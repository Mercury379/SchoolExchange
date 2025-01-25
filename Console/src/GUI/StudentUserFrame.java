package GUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentUserFrame {
    private JPanel panelStudentUser;
    private JTabbedPane tabbedPanel;
    private JTextField textFieldnickname;
    private JTextField textFieldage;
    private JTextField textFieldaddress;
    private JTextField textFieldphonenumer;
    private JRadioButton manRadioButton;
    private JRadioButton womenRadioButton;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel panel7;
    private JPasswordField passwordFieldOld;
    private JPasswordField passwordFieldNew;
    private JButton okButton1;
    private JButton cancelButton1;
    private JPasswordField passwordFieldNew2;
    private JPanel panel8;
    private JPanel panel9;
    private JLabel labelOld;
    private JLabel labelNew;
    private JLabel labelNew2;
    private JPanel panel10;
    private JPanel panel11;
    private JFrame jFrame;
    private AbstractUser studentUser;
    private JFrame superjFrame;
    private JLabel label;
    private String nickname;
    private String age;
    private String sex;
    private String address;
    private String phonenumber;
    private String oldPassword;
    private String newPassword;
    private String newPassword2;
    private boolean isOldPassword;
    private boolean isNewPassword;
    private boolean isNewPassword2;
    public void setSuperjFrame(JFrame jFrame){
        this.superjFrame=jFrame;
    }
    StudentUserFrame(JFrame jFrame, AbstractUser user){
        this.jFrame=jFrame;
        isOldPassword=false;
        isNewPassword=false;
        isNewPassword2=false;
        studentUser=user;
        ImageIcon bg = new ImageIcon("D:/软件工程/Console/res/用户界面.jpg");	//创建一个背景图片
        label=new JLabel(bg);//把背景图片添加到标签里
        label.setBounds(0, 0, bg.getIconWidth()
                ,bg.getIconHeight());	//把标签设置为和图片等高等宽
        jFrame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        jFrame.getLayeredPane().setOpaque(false); //设置透明
        jFrame.setContentPane(panelStudentUser);
        //jFrame.setBackground(new Color(0xAFAFAFA, true));
        //UIManager.put("TabbedPane.contentOpaque",false);
        tabbedPanel.setOpaque(false);
        tabbedPanel.setBackground(new Color(0xAFAFAFA, true));
        panel1.setOpaque(false);
        panel1.setBackground(new Color(0xAFAFAFA, true));
        panel2.setOpaque(false);
        panel2.setBackground(new Color(0xAFAFAFA, true));
        panelStudentUser.setOpaque(false);
        panelStudentUser.setBackground(new Color(0xAFAFAFA, true));
        panel3.setOpaque(false);
        panel4.setOpaque(false);
        panel5.setOpaque(false);
        panel6.setOpaque(false);
        panel7.setOpaque(false);
        panel8.setOpaque(false);
        panel9.setOpaque(false);
        panel10.setOpaque(false);
        panel11.setOpaque(false);
        womenRadioButton.setOpaque(false);
        manRadioButton.setOpaque(false);
        jFrame.setTitle("用户页面");
        jFrame.setLocation(200, 100);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
        changePassword();
        //jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                superjFrame.setVisible(true);
                jFrame.dispose();
                //super.windowClosing(e);
            }
        });
        showSelfInfo();
        sexControl();
        passwordFieldControl();
        buttonControl();
    }
    public void changePassword(){
        if(studentUser.getPassword().equals(studentUser.getId())){
            JOptionPane.showMessageDialog(panelStudentUser, "密码过于简单，有安全风险，请修改密码！");
            tabbedPanel.setSelectedComponent(panel2);
            tabbedPanel.setEnabledAt(0,false);
        }
    }
    public void buttonControl(){
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nickname=textFieldnickname.getText();
                age=textFieldage.getText();
                address=textFieldaddress.getText();
                phonenumber=textFieldphonenumer.getText();
                setDefaultText();
                if(DataProcessing.changeSelfinfo(studentUser.getId(),nickname,age,sex,address,phonenumber)){
                    JOptionPane.showMessageDialog(panelStudentUser, "修改成功！");
                }else{
                    JOptionPane.showMessageDialog(panelStudentUser, "修改失败！");
                }
                showSelfInfo();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSelfInfo();
            }
        });
        okButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isOldPassword && isNewPassword && isNewPassword2){
                    if(DataProcessing.changePassword(studentUser.getId(),newPassword)){
                        tabbedPanel.setEnabledAt(0,true);
                        JOptionPane.showMessageDialog(panelStudentUser, "修改成功！");
                    }else{
                        JOptionPane.showMessageDialog(panelStudentUser, "修改失败！");
                    }
                }else{
                    JOptionPane.showMessageDialog(panelStudentUser, "修改失败！");
                }
            }
        });
        cancelButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordFieldOld.setText("");
                passwordFieldNew.setText("");
                passwordFieldNew2.setText("");
                labelOld.setText("...");
                labelNew.setText("...");
                labelNew2.setText("...");
            }
        });
    }
    public void passwordFieldControl(){
        passwordFieldOld.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                oldPassword=passwordFieldOld.getText();
                try {
                    if(DataProcessing.searchUser(studentUser.getId(),oldPassword)!=null){
                        labelOld.setText("√");
                        isOldPassword=true;
                    }else{
                        labelOld.setText("...");
                        isOldPassword=false;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        passwordFieldNew.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                newPassword=passwordFieldNew.getText();
                if(isFitPassword(newPassword)){
                    labelNew.setText("√");
                    isNewPassword=true;
                }else{
                    labelNew.setText("...");
                    isNewPassword=false;
                }
            }
        });
        passwordFieldNew2.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                newPassword2=passwordFieldNew2.getText();
                if(newPassword2.equals(newPassword)){
                    labelNew2.setText("√");
                    isNewPassword2=true;
                }else{
                    labelNew2.setText("...");
                    isNewPassword2=false;
                }
            }
        });
    }
    public void sexControl(){
        manRadioButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(manRadioButton.isSelected()){
                    womenRadioButton.setSelected(false);
                }else {
                    womenRadioButton.setSelected(true);
                }
            }
        });
        womenRadioButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(womenRadioButton.isSelected()){
                    manRadioButton.setSelected(false);
                }else{
                    manRadioButton.setSelected(true);
                }
            }
        });
    }
    public void showSelfInfo(){
        try {
            ResultSet resultSet=DataProcessing.searchSelfInfo(studentUser.getId());
            resultSet.next();
            nickname=resultSet.getString("nickname");
            age=resultSet.getString("age");
            sex=resultSet.getString("sex");
            address=resultSet.getString("address");
            phonenumber=resultSet.getString("phonenumber");
            if(sex.equals("男")){
                manRadioButton.setSelected(true);
                womenRadioButton.setSelected(false);
            }else{
                womenRadioButton.setSelected(true);
                manRadioButton.setSelected(false);
            }
            textFieldnickname.setText(nickname);
            textFieldage.setText(age);
            textFieldaddress.setText(address);
            textFieldphonenumer.setText(phonenumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setDefaultText(){
        if(nickname.equals("")){
            char[] newId=studentUser.getId().toCharArray();
            for(int i=0;i<newId.length;i++){
                newId[i]= (char) (newId[i]+i);
            }
            nickname=newId.toString();
        }
        if(age.equals("")){
            age="18";
        }
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
}

package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.cert.Certificate;
import java.sql.SQLException;

public class SearchPasswordFrame {
    private JTextField textFieldid;
    private JComboBox comboBoxInstitute;
    private JComboBox comboBoxSpeciality;
    private JTextField textFieldClass;
    private JTextField textFieldName;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JPanel panelSearchPassword;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel panel7;
    private JPanel panel8;
    private JPanel panel9;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel panel10;
    private JLabel labelCertificate;
    private JLabel labelPasswordNew;
    private JLabel labelPasswordNew2;
    private boolean isPassword1;
    private boolean isPassword2;
    private JFrame jFrame;
    private JLabel label;
    private JFrame superjFrame;
    private String institue;
    private String speciality;
    private String sClass;
    private String name;
    private String id;
    private boolean isCertificate;
    private String newPassword1;
    private String newPassword2;
    public void setSuperjFrame(JFrame jFrame){
        superjFrame=jFrame;
    }

    SearchPasswordFrame(JFrame jFrame) {
        this.jFrame = jFrame;
        isCertificate=false;
        isPassword1 = false;
        isPassword2 = false;
        jFrame.setTitle("密码设置页面");
        ImageIcon bg = new ImageIcon("D:/软件工程/Console/res/信息填写.png");    //创建一个背景图片
        label = new JLabel(bg);//把背景图片添加到标签里
        label.setBounds(0, 0, bg.getIconWidth()
                , bg.getIconHeight());    //把标签设置为和图片等高等宽
        jFrame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        jFrame.getLayeredPane().setOpaque(false); //设置透明
        jFrame.setContentPane(panelSearchPassword);
        panelSearchPassword.setOpaque(false);
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);
        panel4.setOpaque(false);
        panel5.setOpaque(false);
        panel6.setOpaque(false);
        panel7.setOpaque(false);
        panel8.setOpaque(false);
        panel9.setOpaque(false);
        panel10.setOpaque(false);
        jFrame.setLocation(200, 100);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
        comboBoxSpeciality.addItem("");
        //jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                superjFrame.setVisible(true);
                jFrame.dispose();
                //super.windowClosing(e);
            }
        });
        comboBoxInstitute.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                setComboBox();
            }
        });
        textFieldName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {Certificate();}
        });
        passwordField1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                newPassword1=passwordField1.getText();
                if(isFitPassword(newPassword1)){
                    labelPasswordNew.setText("√");
                    isPassword1=true;
                }else{
                    labelPasswordNew.setText("...");
                    isPassword1=false;
                }
            }
        });
        passwordField2.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                newPassword2=passwordField2.getText();
                if(newPassword1.equals(newPassword2)){
                    labelPasswordNew2.setText("√");
                    isPassword2=true;
                }else{
                    labelPasswordNew2.setText("...");
                    isPassword2=false;
                }
            }
        });
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldid.setText("");
                textFieldName.setText("");
                textFieldClass.setText("");
                comboBoxSpeciality.setSelectedIndex(0);
                comboBoxInstitute.setSelectedIndex(0);
                passwordField1.setText("");
                passwordField2.setText("");
                labelPasswordNew.setText("...");
                labelPasswordNew2.setText("...");
            }
        });
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isPassword1&&isPassword2&&isCertificate){
                    id=textFieldid.getText();
                    if(DataProcessing.changePassword(id,newPassword1)){
                        JOptionPane.showMessageDialog(panelSearchPassword, "找回密码成功！");
                        try {
                            AbstractUser user=DataProcessing.searchUser(id,newPassword1);
                            if(user.getRole().equals("studentuser")){
                                StudentUserFrame studentUserFrame=new StudentUserFrame(new JFrame(),user);
                                studentUserFrame.setSuperjFrame(superjFrame);
                            }else{
                                AdministratorFrame administratorFrame=new AdministratorFrame(new JFrame(),user);
                                administratorFrame.setSuperjFrame(superjFrame);
                            }
                            jFrame.setVisible(false);

                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }else{
                        JOptionPane.showMessageDialog(panelSearchPassword, "找回密码失败！");
                    }
                }else {
                    JOptionPane.showMessageDialog(panelSearchPassword, "找回密码失败！");
                }
            }
        });
    }
    public void Certificate() {
        id = textFieldid.getText();
        name = textFieldName.getText();
        institue = (String) comboBoxInstitute.getSelectedItem();
        speciality = (String) comboBoxSpeciality.getSelectedItem();
        sClass = textFieldClass.getText();
        try {
            if (DataProcessing.certificateStudent(id, institue, speciality, sClass, name)&&DataProcessing.certificateStudentid(id)) {
                labelCertificate.setText("身份验证成功！");
                //labelCertificate.setBackground(new Color(0xF2373C));
                isCertificate=true;
            } else if(DataProcessing.certificateStudent(id, institue, speciality, sClass, name)&&!DataProcessing.certificateStudentid(id)){
                JOptionPane.showMessageDialog(panelSearchPassword, "尚未注册，请先注册！");
            }
            else {
                labelCertificate.setText("学生身份验证中...");
                isCertificate=false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        public void setComboBox(){
        institue=(String)comboBoxInstitute.getSelectedItem();
        if(institue.equals("材料学院")){
            comboBoxSpeciality.removeAllItems();
            comboBoxSpeciality.addItem((Object) "");
            comboBoxSpeciality.addItem((Object) "复合材料科学与工程");
            comboBoxSpeciality.addItem((Object) "材料化学");
            comboBoxSpeciality.addItem((Object) "新能源材料");
            comboBoxSpeciality.addItem((Object) "材料科学与工程");
            comboBoxSpeciality.addItem((Object) "高分子材料");
            comboBoxSpeciality.addItem((Object) "无机非金属材料");
        }else if(institue.equals("计算机与人工智能学院")){
            comboBoxSpeciality.removeAllItems();
            comboBoxSpeciality.addItem((Object) "");
            comboBoxSpeciality.addItem((Object) "计算机卓越工程师");
            comboBoxSpeciality.addItem((Object) "计算机科学与技术");
            comboBoxSpeciality.addItem((Object) "软件工程");
            comboBoxSpeciality.addItem((Object) "大数据");
            comboBoxSpeciality.addItem((Object) "人工智能");
            comboBoxSpeciality.addItem((Object) "物联网");
        }else if(institue.equals("管理学院")){
            comboBoxSpeciality.removeAllItems();
            comboBoxSpeciality.addItem((Object) "");
            comboBoxSpeciality.addItem((Object) "营销");
            comboBoxSpeciality.addItem((Object) "会计");
            comboBoxSpeciality.addItem((Object) "财务管理");
            comboBoxSpeciality.addItem((Object) "工商管理");
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

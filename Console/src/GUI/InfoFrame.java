package GUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InfoFrame {
    private JTextField textNickname;
    private JTextField textAge;
    private JTextField textAddress;
    private JTextField textNumber;
    private JRadioButton menradioButton;
    private JRadioButton womenradioButton;
    private JPanel panelInfo;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel panel7;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel panel8;
    private JFrame jFrame;
    private JLabel label;
    private String id;
    private JFrame superjFrame;
    private JFrame loginjFrame;
    String nickname;
    String age;
    String password;
    public void setId(String id) {this.id = id;}
    public void setPassword(String password) {this.password = password;}
    public void setSuperjFrame(JFrame jFrame){
        superjFrame=jFrame;
    }
    public void setLoginjFrame(JFrame jFrame){loginjFrame=jFrame;}
    InfoFrame(JFrame jFrame) {
        this.jFrame = jFrame;
        jFrame.setTitle("信息填写页面");
        ImageIcon bg = new ImageIcon("D:/软件工程/Console/res/信息填写1.jpg");    //创建一个背景图片
        label = new JLabel(bg);//把背景图片添加到标签里
        label.setBounds(0, 0, bg.getIconWidth()
                , bg.getIconHeight());    //把标签设置为和图片等高等宽
        jFrame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        jFrame.getLayeredPane().setOpaque(false); //设置透明
        jFrame.setContentPane(panelInfo);
        panelInfo.setOpaque(false);
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);
        panel4.setOpaque(false);
        panel5.setOpaque(false);
        panel6.setOpaque(false);
        panel7.setOpaque(false);
        panel8.setOpaque(false);
        menradioButton.setOpaque(false);
        womenradioButton.setOpaque(false);
        jFrame.setLocation(200, 100);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
        textAddress.setText("必填");
        textNumber.setText("必填");
        //jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                superjFrame.setVisible(true);
                //super.windowClosing(e);
            }
        });
        menradioButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                boolean isMenSelected=menradioButton.isSelected();
                womenradioButton.setSelected(!isMenSelected);
            }
        });
        womenradioButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                boolean isMenSelected=womenradioButton.isSelected();
                menradioButton.setSelected(!isMenSelected);
            }
        });
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nickname=textNickname.getText();
                age=textAge.getText();
                setDefaultText();
                String address=textAddress.getText();
                String phonenumber=textNumber.getText();
                String sex;
                if(womenradioButton.isSelected()){
                    sex="女";
                }else{
                    sex="男";
                }
                if(DataProcessing.insertInfo(id,nickname,age,sex,address,phonenumber)){
                    StudentUser user=new StudentUser(id,password,"studentuser");
                    StudentUserFrame studentUserFrame=new StudentUserFrame(new JFrame(),user);
                    studentUserFrame.setSuperjFrame(loginjFrame);
                    jFrame.dispose();
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataProcessing.deleteUser(id);
                superjFrame.setVisible(true);
                jFrame.dispose();
            }
        });
    }
    public void setDefaultText(){
        if(nickname.equals("")){
            char[] newId=id.toCharArray();
            for(int i=0;i<newId.length;i++){
                newId[i]= (char) (newId[i]+i);
            }
            nickname=newId.toString();
        }
        if(age.equals("")){
            age="18";
        }
    }
}

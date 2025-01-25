package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class NewFrame implements ActionListener {
    private JTextField textId;
    private JComboBox comboBoxInstitute;
    private JTextField textClass;
    private JComboBox comboBoxSpeciality;
    private JTextField textName;
    private JPanel panelNew;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel panel7;
    private JFrame jFrame;
    private JLabel label;
    private JFrame superjFrame;
    public void setSuperjFrame(JFrame jFrame){
        superjFrame=jFrame;
    }
    public NewFrame(JFrame jFrame) {
        this.jFrame=jFrame;
        jFrame.setTitle("身份认证页面");
        ImageIcon bg = new ImageIcon("D:/软件工程/Console/res/注册界面2.jpg");	//创建一个背景图片
        label=new JLabel(bg);//把背景图片添加到标签里
        label.setBounds(0, 0, bg.getIconWidth()
                ,bg.getIconHeight());	//把标签设置为和图片等高等宽
        jFrame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        jFrame.getLayeredPane().setOpaque(false); //设置透明
        jFrame.setContentPane(panelNew);
        panelNew.setOpaque(false);
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);
        panel4.setOpaque(false);
        panel5.setOpaque(false);
        panel6.setOpaque(false);
        panel7.setOpaque(false);
        //jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocation(200,100);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                superjFrame.setVisible(true);
                //super.windowClosing(e);
            }
        });
        textControl();
        comboBoxSpeciality.addItem("");
        buttonOK.addActionListener(this);
        buttonCancel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String id = textId.getText();
            String name = textName.getText();
            String institue = (String) comboBoxInstitute.getSelectedItem();
            String speciality = (String) comboBoxSpeciality.getSelectedItem();
            String Class = textClass.getText();
            if (e.getActionCommand().equals(buttonOK.getText())) {
                if(DataProcessing.certificateStudent(id, institue, speciality, Class, name) && !DataProcessing.certificateStudentid(id)){
                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //JOptionPane.showMessageDialog(panelNew, "验证成功！");
                                RegisterFrame registerFrame=new RegisterFrame(new JFrame());
                                registerFrame.setSuperjFrame(jFrame);
                                registerFrame.setId(id);
                                registerFrame.setLoginjFrame(superjFrame);
                                jFrame.setVisible(false);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }});
                }else if(DataProcessing.searchIsUser(id)){
                    JOptionPane.showMessageDialog(panelNew, "已是用户，无需注册！");
                }else{
                    JOptionPane.showMessageDialog(panelNew, "学生身份验证失败");
                }
            }else if(e.getActionCommand().equals(buttonCancel.getText())){
                superjFrame.setVisible(true);
                jFrame.dispose();
            }
        }catch (SQLException ex) {
                throw new RuntimeException(ex);
        }
    }
    public void textControl(){
//        textId.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                id =textId.getText();
//            }
//        });
//        textName.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                name=textName.getText();
//            }
//        });
        comboBoxInstitute.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String institue=(String)comboBoxInstitute.getSelectedItem();
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
        });
    }
}

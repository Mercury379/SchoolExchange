package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class TestFrame {
    private JPanel panelTest;
    JFrame jFrame;
    JLabel label;
    public TestFrame(JFrame jFrame){
        this.jFrame=jFrame;
        jFrame.setTitle("登录页面");
        ImageIcon bg = new ImageIcon("D:/软件工程/登陆界面.jpg");	//创建一个背景图片
        label=new JLabel(bg);//把背景图片添加到标签里
        label.setBounds(0, 0, 500, 400);	//把标签设置为和图片等高等宽
        jFrame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        jFrame.getLayeredPane().setOpaque(false); //设置透明
        jFrame.setContentPane(panelTest);
        panelTest.setOpaque(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocation(200,100);
        //jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}

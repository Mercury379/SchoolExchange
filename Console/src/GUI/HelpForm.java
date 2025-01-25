package GUI;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HelpForm {
    private JPanel panelHelp;
    private JTabbedPane tabbedPane1;
    private JFrame jFrame;
    private JFrame superjFrame;
    public void setSuperjFrame(JFrame jFrame){
        superjFrame=jFrame;
    }
    public HelpForm(JFrame jFrame){
        this.jFrame=jFrame;
        jFrame.setContentPane(panelHelp);
        jFrame.setTitle("帮助页面");
//        ImageIcon bg = new ImageIcon("D:/软件工程/登陆界面.jpg");	//创建一个背景图片
//        label=new JLabel(bg);//把背景图片添加到标签里
//        label.setBounds(0, 0, 500, 400);	//把标签设置为和图片等高等宽
//        jFrame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
//        jFrame.getLayeredPane().setOpaque(false); //设置透明
//        jFrame.setContentPane(panelHelp);
//        panelHelp.setOpaque(false);
        //jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                superjFrame.setVisible(true);
                //super.windowClosing(e);
            }
        });
        jFrame.setLocation(200,100);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}

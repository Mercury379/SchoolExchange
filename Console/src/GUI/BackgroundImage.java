package GUI;

import javax.swing.*;
import java.awt.*;

public class BackgroundImage {
    public BackgroundImage(JFrame frame, Container container, String ImageName) {
        // 限定加载图片路径
        ImageIcon icon= new ImageIcon("D:/软件工程/Console/src/" + ImageName);

        final JLabel labelBackground = new JLabel();
        ImageIcon iconBookManageSystemBackground = icon;
        labelBackground.setIcon(iconBookManageSystemBackground);
        // 设置label的大小
        labelBackground.setBounds(0,0,iconBookManageSystemBackground.getIconWidth()
                ,iconBookManageSystemBackground.getIconHeight());
        //将背景图片标签放入桌面面板的最底层
        frame.getLayeredPane().add(labelBackground,new Integer(Integer.MIN_VALUE));
        //frame.add(labelBackground);
        // 将容器转换为面板设置为透明
        frame.getLayeredPane().setOpaque(false);
        JPanel panel = (JPanel)container;
        panel.setOpaque(false);

    }
}

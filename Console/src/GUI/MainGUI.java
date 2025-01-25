package GUI;

import javax.swing.*;
import java.awt.*;

public class MainGUI {
    public  static void main(String[] args) {
        DataProcessing.connectToDatabase();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    JFrame jFrame = new JFrame();
                    UIManager.put("TabbedPane.contentOpaque",false);
                    //UIManager.put("Tabble.contentOpaque",false);
                    //UIManager.put("ScrollPane.contentOpaque",false);
                    //HelpForm helpForm =new HelpForm();
                    LoginFrame loginFrame = new LoginFrame(jFrame);
                    //InfoFrame infoFrame =new InfoFrame(jFrame);
                    DataProcessing.zeroErrorCount();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                //在此处添加退出应用程序前需完成工作，如：关闭网络连接、关闭数据库连接等
                //DataProcessing.disconnectFromDataBase();
                System.out.println("应用程序退出！");
            }
        });
    }
}

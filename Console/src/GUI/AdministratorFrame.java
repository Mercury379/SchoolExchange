package GUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorFrame {
    private JTabbedPane Panel0;
    private JTable tableUser;
    private JPanel panelAdministrator;
    private JPanel searchPanel;
    private JTextField textId;
    private JLabel idLabel;
    private JButton searchButton;
    private JTextField textName;
    private JLabel nameLabel;
    private JComboBox comboBoxInstitue;
    private JComboBox comboBoxSpecial;
    private JButton powerButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JScrollPane panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel0;
    private JPanel panelSelf;
    private JTextField textClass;
    private JPanel panel7;
    private JTable tableStudent;
    private JTextField textId2;
    private JTextField textName2;
    private JButton newUserButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton searchButton2;
    private JTextField textClass2;
    private JPanel panel6;
    private JTextField textFieldNickname;
    private JTextField textFieldAge;
    private JTextField textFieldAddress;
    private JTextField textFieldPhonenumber;
    private JRadioButton manRadioButton;
    private JRadioButton womenRadioButton;
    private JPasswordField passwordFieldOld;
    private JPasswordField passwordFieldNew;
    private JPasswordField passwordFieldNew2;
    private JButton okUpdateButton3;
    private JButton cancelButton3;
    private JButton okUpdateButton;
    private JButton cancelButton2;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel8;
    private JPanel panel9;
    private JPanel panel10;
    private JPanel panel11;
    private JPanel panel12;
    private JLabel labelOld;
    private JLabel labelNew;
    private JLabel labelNew2;
    private JButton noenableButton;
    private JButton enableButton;
    private JPanel panel13;
    private JPanel panel14;
    private JTable tableOperation;
    private JPanel panel15;
    private JScrollPane panel16;
    private AbstractUser administrator;
    private JLabel backgroundlabel;
    private JLabel label;
    private DefaultTableModel tableModelUsers;
    private DefaultTableModel tableModelStudents;
    private DefaultTableModel tableModelOperations;
    private JFrame superjFrame;
    public String id;
    public String name;
    public String institue;
    public String speciality;
    public String Class;
    public String id2;
    public String name2;
    public String institue2;
    public String speciality2;
    public String Class2;
    public JFrame jFrame;
    private boolean isOldPassword;
    private boolean isNewPassword;
    private boolean isNewPassword2;
    private String oldPassword;
    private String newPassword;
    private String newPassword2;
    public void setSuperjFrame(JFrame jFrame){
        superjFrame=jFrame;
    }
    public AdministratorFrame(JFrame jFrame,AbstractUser User) {
        this.jFrame=jFrame;
        isOldPassword=false;
        isNewPassword=false;
        isNewPassword2=false;
        administrator = User;
        jFrame.setTitle("系统管理员页面");
        //jFrame.setContentPane(panelAdministrator);
        ImageIcon bg = new ImageIcon("D:/软件工程/Console/res/管理员界面5.jpg");	//创建一个背景图片
        label=new JLabel(bg);//把背景图片添加到标签里
        label.setBounds(0, 0, bg.getIconWidth()
                ,bg.getIconHeight());	//把标签设置为和图片等高等宽
        jFrame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        jFrame.getLayeredPane().setOpaque(false); //设置透明
        jFrame.setContentPane(panelAdministrator);
        panelAdministrator.setOpaque(false);
        //UIManager.put("searchPanel.contentOpaque",false);
        searchPanel.setOpaque(false);
        //searchPanel.setBackground(new Color(0x0EBEBF6, true));
        panel0.setOpaque(false);
        Panel0.setOpaque(false);
        Panel0.setBackground(new Color(0x0FDFDFF, true));
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);
        panel4.setOpaque(false);
        panel5.setOpaque(false);
        panel7.setOpaque(false);
        panel6.setOpaque(false);
        panel8.setOpaque(false);
        panel9.setOpaque(false);
        panel10.setOpaque(false);
        panel11.setOpaque(false);
        panel12.setOpaque(false);
        panel13.setOpaque(false);
        panel14.setOpaque(false);
        panel15.setOpaque(false);
        panel6.setOpaque(false);

        manRadioButton.setOpaque(false);
        womenRadioButton.setOpaque(false);
        //panel7.setBackground(new Color(0x0E1E1E7, true));
        panelSelf.setOpaque(false);
        tableUser.setOpaque(false);
        //tableUser.setOpaque(false);
        //jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocation(200, 100);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
        ResultSet resultSet = DataProcessing.listUser();
        showUserList(resultSet);
        ResultSet resultSet1=DataProcessing.listStudent();
        showStudentList(resultSet1);
        ResultSet resultSet2=DataProcessing.listOperation(administrator.getId());
        showOperationList(resultSet2);
        textControl();
        buttonControl();
        passwordFieldControl();
        sexControl();
        showSelfInfo();
        changePassword();
        comboBoxSpecial.addItem("");
        comboBox2.addItem("");
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                superjFrame.setVisible(true);
                //super.windowClosing(e);
            }
        });
    }
    public void changePassword(){
        if(administrator.getPassword().equals(administrator.getId())){
            JOptionPane.showMessageDialog(panelAdministrator, "密码过于简单，有安全风险，请修改密码！");
            Panel0.setSelectedComponent(panel10);
            Panel0.setEnabledAt(0,false);
            Panel0.setEnabledAt(1,false);
            Panel0.setEnabledAt(2,false);
            Panel0.setEnabledAt(4,false);
        }
    }
    public void showStudentList(ResultSet resultSet){
        try{
            String[] colName={"学号","学院","专业","班级","姓名"};
            String[][] tableValue;
            ResultSet resultset=DataProcessing.listStudent();
            int row=0;
            while(resultset.next()){
                row++;
            }
            tableValue=new String[row][5];
            row=0;
            while(resultSet.next()){
                tableValue[row][0]=resultSet.getString("studentid");
                tableValue[row][1]=resultSet.getString("institute");
                tableValue[row][2]=resultSet.getString("speciality");
                tableValue[row][3]=resultSet.getString("class");
                tableValue[row][4]=resultSet.getString("name");
                row++;
            }
            tableModelStudents = new DefaultTableModel(tableValue,colName);
            tableStudent.setModel(tableModelStudents);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void showUserList(ResultSet resultSet){
        try{
            String[] colName={"学号","学院","专业","班级","姓名","密码","是否管理员","是否冻结"};
            String[][] tableValue;
            ResultSet resultset=DataProcessing.listUser();
            int row=0;
            while(resultset.next()){
                row++;
            }
            tableValue=new String[row][8];
            row=0;
            while(resultSet.next()){
                tableValue[row][0]=resultSet.getString("studentid");
                tableValue[row][1]=resultSet.getString("institute");
                tableValue[row][2]=resultSet.getString("speciality");
                tableValue[row][3]=resultSet.getString("class");
                tableValue[row][4]=resultSet.getString("name");
                tableValue[row][5]=resultSet.getString("password");
                tableValue[row][6]=resultSet.getString("isAdministrator");
                tableValue[row][7]=resultSet.getString("isEnable");
                row++;
            }
            tableModelUsers = new DefaultTableModel(tableValue,colName);
            tableUser.setModel(tableModelUsers);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void showOperationList(ResultSet resultSet){
        try{
            String[] colName={"被操作者学号","被操作者姓名","操作者学号","操作者姓名","具体操作","操作时间"};
            String[][] tableValue;
            ResultSet resultset=DataProcessing.listOperation(administrator.getId());
            int row=0;
            while(resultset.next()){
                row++;
            }
            tableValue=new String[row][6];
            row=0;
            while(resultSet.next()){
                tableValue[row][0]=resultSet.getString("UserId");
                tableValue[row][1]=resultSet.getString("UserName");
                tableValue[row][2]=resultSet.getString("AdministratorId");
                tableValue[row][3]=resultSet.getString("AdministratorName");
                tableValue[row][4]=resultSet.getString("Operation");
                tableValue[row][5]=resultSet.getString("Time");
                row++;
            }
            tableModelOperations = new DefaultTableModel(tableValue,colName);
            tableOperation.setModel(tableModelOperations);
        } catch (Exception e){
            e.printStackTrace();
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
        comboBoxInstitue.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                institue=(String)comboBoxInstitue.getSelectedItem();
                if(institue.equals("材料学院")){
                    comboBoxSpecial.removeAllItems();
                    comboBoxSpecial.addItem((Object) "");
                    comboBoxSpecial.addItem((Object) "复合材料科学与工程");
                    comboBoxSpecial.addItem((Object) "材料化学");
                    comboBoxSpecial.addItem((Object) "新能源材料");
                    comboBoxSpecial.addItem((Object) "材料科学与工程");
                    comboBoxSpecial.addItem((Object) "高分子材料");
                    comboBoxSpecial.addItem((Object) "无机非金属材料");
                }else if(institue.equals("计算机与人工智能学院")){
                    comboBoxSpecial.removeAllItems();
                    comboBoxSpecial.addItem((Object) "");
                    comboBoxSpecial.addItem((Object) "计算机卓越工程师");
                    comboBoxSpecial.addItem((Object) "计算机科学与技术");
                    comboBoxSpecial.addItem((Object) "软件工程");
                    comboBoxSpecial.addItem((Object) "大数据");
                    comboBoxSpecial.addItem((Object) "人工智能");
                    comboBoxSpecial.addItem((Object) "物联网");
                }else if(institue.equals("管理学院")){
                    comboBoxSpecial.removeAllItems();
                    comboBoxSpecial.addItem((Object) "");
                    comboBoxSpecial.addItem((Object) "营销");
                    comboBoxSpecial.addItem((Object) "会计");
                    comboBoxSpecial.addItem((Object) "财务管理");
                    comboBoxSpecial.addItem((Object) "工商管理");
                }
            }
        });
        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                institue2=(String)comboBox1.getSelectedItem();
                if(institue2.equals("材料学院")){
                    comboBox2.removeAllItems();
                    comboBox2.addItem((Object) "");
                    comboBox2.addItem((Object) "复合材料科学与工程");
                    comboBox2.addItem((Object) "材料化学");
                    comboBox2.addItem((Object) "新能源材料");
                    comboBox2.addItem((Object) "材料科学与工程");
                    comboBox2.addItem((Object) "高分子材料");
                    comboBox2.addItem((Object) "无机非金属材料");
                }else if(institue2.equals("计算机与人工智能学院")){
                    comboBox2.removeAllItems();
                    comboBox2.addItem((Object) "");
                    comboBox2.addItem((Object) "计算机卓越工程师");
                    comboBox2.addItem((Object) "计算机科学与技术");
                    comboBox2.addItem((Object) "软件工程");
                    comboBox2.addItem((Object) "大数据");
                    comboBox2.addItem((Object) "人工智能");
                    comboBox2.addItem((Object) "物联网");
                }else if(institue2.equals("管理学院")){
                    comboBoxSpecial.removeAllItems();
                    comboBoxSpecial.addItem((Object) "");
                    comboBoxSpecial.addItem((Object) "营销");
                    comboBoxSpecial.addItem((Object) "会计");
                    comboBoxSpecial.addItem((Object) "财务管理");
                    comboBoxSpecial.addItem((Object) "工商管理");
                }
            }
        });
    }

    public void buttonControl(){
        searchButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    id2 = textId2.getText();
                    name2= textName2.getText();
                    institue2=(String)comboBox1.getSelectedItem();
                    speciality2=(String)comboBox2.getSelectedItem();
                    Class2= textClass2.getText();
                    ResultSet resultSet = null;
                    resultSet=DataProcessing.searchStudent(id2,name2,institue2,speciality2,Class2);
                    if(resultSet==null) {
                        JOptionPane.showMessageDialog(searchPanel, "查找不到该用户！");
                    }else{
                        showStudentList(resultSet);
                    }
                } catch (SQLException E) {
                    throw new RuntimeException(E);
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    id =textId.getText();
                    name=textName.getText();
                    institue=(String)comboBoxInstitue.getSelectedItem();
                    speciality=(String)comboBoxSpecial.getSelectedItem();
                    Class=textClass.getText();
                    ResultSet resultSet = null;
                    resultSet=DataProcessing.searchStudentUser(id,name,institue,speciality,Class);
                    if(resultSet==null) {
                        JOptionPane.showMessageDialog(searchPanel, "查找不到该用户！");
                    }else{
                        showUserList(resultSet);
                    }
                } catch (SQLException E) {
                    throw new RuntimeException(E);
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentRow = tableUser.getSelectedRow();
                if(currentRow==-1){
                    JOptionPane.showMessageDialog(searchPanel, "请选中一位用户！");
                }else {
                    Object object = tableModelUsers.getValueAt(currentRow, 0);
                    id = object.toString();
                    object=tableModelUsers.getValueAt(currentRow, 6);
                    String role=object.toString();
                    if (administrator.getId().equals(id)) {
                        JOptionPane.showMessageDialog(searchPanel, "不能重置自身密码！");
                    } else if(role.equals("1")){
                        JOptionPane.showMessageDialog(searchPanel, "不能重置管理员的密码！");
                    } else {
                        DataProcessing.updatePassword(id);
                        DataProcessing.insertOperation(administrator.getId(),id,"重置密码");
                        showOperationList(DataProcessing.listOperation(administrator.getId()));
                        id = textId.getText();
                        name = textName.getText();
                        institue = (String) comboBoxInstitue.getSelectedItem();
                        speciality = (String) comboBoxSpecial.getSelectedItem();
                        Class = textClass.getText();
                        ResultSet resultSet = null;
                        try {
                            resultSet = DataProcessing.searchStudentUser(id, name, institue, speciality, Class);
                            showUserList(resultSet);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentRow = tableUser.getSelectedRow();
                if(currentRow!=-1){
                    Object object = tableModelUsers.getValueAt(currentRow, 0);
                    Object object2 = tableModelUsers.getValueAt(currentRow, 6);
                    String role=object2.toString();
                    id = object.toString();
                    if(administrator.getId().equals(id)){
                        JOptionPane.showMessageDialog(searchPanel, "不能删除自身！");
                    }else if(role.equals("1")){
                        JOptionPane.showMessageDialog(searchPanel, "不能删除管理员！");
                    }else{
                        DataProcessing.deleteStudentUser(id);
                        DataProcessing.insertOperation(administrator.getId(),id,"删除用户");
                        showOperationList(DataProcessing.listOperation(administrator.getId()));
                        UpdateTable();
                    }
                }else{
                    JOptionPane.showMessageDialog(searchPanel, "请选中一位用户！");
                }
            }
        });
        powerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentRow = tableUser.getSelectedRow();
                if(currentRow==-1){
                    JOptionPane.showMessageDialog(searchPanel, "请选中一位用户！");
                }else {
                    Object object = tableModelUsers.getValueAt(currentRow, 0);
                    id = object.toString();
                    Object object2 = tableModelUsers.getValueAt(currentRow, 6);
                    String role = object2.toString();
                    if (administrator.getId().equals(id)) {
                        JOptionPane.showMessageDialog(searchPanel, "不能更改自身权限！");
                    } else if (role.equals("1")) {
                        JOptionPane.showMessageDialog(searchPanel, "不能更改管理员权限！");
                    } else {
                        DataProcessing.updateRole(id);
                        DataProcessing.insertOperation(administrator.getId(),id,"授权用户");
                        showOperationList(DataProcessing.listOperation(administrator.getId()));
                        id = textId.getText();
                        name = textName.getText();
                        institue = (String) comboBoxInstitue.getSelectedItem();
                        speciality = (String) comboBoxSpecial.getSelectedItem();
                        Class = textClass.getText();
                        ResultSet resultSet = null;
                        try {
                            resultSet = DataProcessing.searchStudentUser(id, name, institue, speciality, Class);
                            showUserList(resultSet);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });
        okUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickname=textFieldNickname.getText();
                String age=textFieldAge.getText();
                String address=textFieldAddress.getText();
                String phonenumber=textFieldPhonenumber.getText();
                String sex;
                if(womenRadioButton.isSelected()){
                    sex="女";
                }else{
                    sex="男";
                }
                if(nickname.equals("")){
                    char[] newId=administrator.getId().toCharArray();
                    for(int i=0;i<newId.length;i++){
                        newId[i]= (char) (newId[i]+i);
                    }
                    nickname=newId.toString();
                }
                if(age.equals("")){
                    age="18";
                }
                if(DataProcessing.changeSelfinfo(administrator.getId(),nickname,age,sex,address,phonenumber)){
                    JOptionPane.showMessageDialog(panelAdministrator, "修改成功！");
                }else{
                    JOptionPane.showMessageDialog(panelAdministrator, "修改失败！");
                }
                showSelfInfo();
            }
        });
        cancelButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSelfInfo();
            }
        });
        okUpdateButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isOldPassword && isNewPassword && isNewPassword2){
                    if(DataProcessing.changePassword(administrator.getId(),newPassword)){
                        JOptionPane.showMessageDialog(panelAdministrator, "修改成功！");
                        Panel0.setEnabledAt(0,true);
                        Panel0.setEnabledAt(1,true);
                        Panel0.setEnabledAt(2,true);
                        Panel0.setEnabledAt(4,true);
                    }else{
                        JOptionPane.showMessageDialog(panelAdministrator, "修改失败！");
                    }
                }else{
                    JOptionPane.showMessageDialog(panelAdministrator, "修改失败！");
                }
            }
        });
        cancelButton3.addActionListener(new ActionListener() {
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
        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentRow = tableStudent.getSelectedRow();
                if (currentRow==-1){
                    JOptionPane.showMessageDialog(panelAdministrator, "请选中一位学生！");
                }else {
                    Object object = tableModelStudents.getValueAt(currentRow, 0);
                    id = object.toString();
                    char[] newId=id.toCharArray();
                    for(int i=0;i<newId.length;i++){
                        newId[i]= (char) (newId[i]+i);
                    }
                    String nickname=newId.toString();
                    if(DataProcessing.insertUser(id,id,"0","0") && DataProcessing.insertInfo(id,nickname,"18","男","武汉理工大学","-----------")){
                        DataProcessing.insertOperation(administrator.getId(),id,"新增用户");
                        showOperationList(DataProcessing.listOperation(administrator.getId()));
                        UpdateTable();
                    }else{
                        JOptionPane.showMessageDialog(panelAdministrator, "新增失败！");
                    }
                }
            }
        });
        noenableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentRow = tableUser.getSelectedRow();
                if(currentRow==-1){
                    JOptionPane.showMessageDialog(searchPanel, "请选中一位用户！");
                }else {
                    Object object = tableModelUsers.getValueAt(currentRow, 0);
                    id = object.toString();
                    Object object2 = tableModelUsers.getValueAt(currentRow, 7);
                    String enable = object2.toString();
                    if (administrator.getId().equals(id)) {
                        JOptionPane.showMessageDialog(searchPanel, "不能更改自身权冻结状态！");
                    } else if (enable.equals("1")) {
                        JOptionPane.showMessageDialog(searchPanel, "不能冻结已冻结用户！");
                    } else {
                        DataProcessing.updateEnable(id,"1");
                        DataProcessing.insertOperation(administrator.getId(),id,"冻结用户");
                        showOperationList(DataProcessing.listOperation(administrator.getId()));
                        id = textId.getText();
                        name = textName.getText();
                        institue = (String) comboBoxInstitue.getSelectedItem();
                        speciality = (String) comboBoxSpecial.getSelectedItem();
                        Class = textClass.getText();
                        ResultSet resultSet = null;
                        try {
                            resultSet = DataProcessing.searchStudentUser(id, name, institue, speciality, Class);
                            showUserList(resultSet);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });
        enableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentRow = tableUser.getSelectedRow();
                if(currentRow==-1){
                    JOptionPane.showMessageDialog(searchPanel, "请选中一位用户！");
                }else {
                    Object object = tableModelUsers.getValueAt(currentRow, 0);
                    id = object.toString();
                    Object object2 = tableModelUsers.getValueAt(currentRow, 7);
                    String enable = object2.toString();
                    if (administrator.getId().equals(id)) {
                        JOptionPane.showMessageDialog(searchPanel, "不能更改自身权冻结状态！");
                    } else if (enable.equals("0")) {
                        JOptionPane.showMessageDialog(searchPanel, "不能解冻非冻结用户！");
                    } else {
                        DataProcessing.updateEnable(id,"0");
                        DataProcessing.insertOperation(administrator.getId(),id,"解冻用户");
                        showOperationList(DataProcessing.listOperation(administrator.getId()));
                        id = textId.getText();
                        name = textName.getText();
                        institue = (String) comboBoxInstitue.getSelectedItem();
                        speciality = (String) comboBoxSpecial.getSelectedItem();
                        Class = textClass.getText();
                        ResultSet resultSet = null;
                        try {
                            resultSet = DataProcessing.searchStudentUser(id, name, institue, speciality, Class);
                            showUserList(resultSet);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
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
    public void showSelfInfo(){
        try {
            ResultSet resultSet=DataProcessing.searchSelfInfo(administrator.getId());
            resultSet.next();
            String nickname=resultSet.getString("nickname");
            String age=resultSet.getString("age");
            String sex=resultSet.getString("sex");
            String address=resultSet.getString("address");
            String phonenumber=resultSet.getString("phonenumber");
            if(sex.equals("男")){
                manRadioButton.setSelected(true);
                womenRadioButton.setSelected(false);
            }else{
                womenRadioButton.setSelected(true);
                manRadioButton.setSelected(false);
            }
            textFieldNickname.setText(nickname);
            textFieldAge.setText(age);
            textFieldAddress.setText(address);
            textFieldPhonenumber.setText(phonenumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
                    if(DataProcessing.searchUser(administrator.getId(),oldPassword)!=null){
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
    public void UpdateTable(){
        id=textId.getText();
        id2=textId2.getText();
        name=textName.getText();
        name2=textName2.getText();
        institue=(String)comboBoxInstitue.getSelectedItem();
        institue2=(String)comboBox1.getSelectedItem();
        speciality=(String)comboBoxSpecial.getSelectedItem();
        speciality2=(String)comboBox2.getSelectedItem();
        Class=textClass.getText();
        Class2=textClass2.getText();
        ResultSet resultSet1= null;
        try {
            resultSet1 = DataProcessing.searchStudentUser(id,name,institue,speciality,Class);
            ResultSet resultSet2 = DataProcessing.searchStudent(id2,name2,institue2,speciality2,Class2);
            showStudentList(resultSet2);
            showUserList(resultSet1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

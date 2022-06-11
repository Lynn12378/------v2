import java.awt.event.*;
import javax.swing.*;

import java.awt.*;

public class User_Interface implements ActionListener
{
	JFrame frame;
	JPanel panel,panel2,panel3;
	JComboBox<String> comboBox;
	JScrollPane jsp;
	JLabel accLB=new JLabel("帳號");
	JLabel passLB=new JLabel("密碼");
	JLabel nameLB=new JLabel("姓名");
	JLabel idLB=new JLabel("身分");
    JButton confirm=new JButton("確認");
    JButton back=new JButton("返回");
	protected JTextField accTF,nameTF;
	protected JPasswordField passTF;
	protected String password,id;
	protected User user;
	public int w=700,h=650;

	public static void main(String[] args)
	{
		User_Interface ui=new User_Interface();
        ui.origin_panel();
	}

	public User_Interface()
	{
		frame = new JFrame("圖書館");
		frame.setResizable(false);
		frame.setSize(w,h);
		frame.setLocationRelativeTo(null);//置中開啟(取消從左上開啟)
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				int result = JOptionPane.showConfirmDialog(frame,
						"是否關閉程式",
						"警告",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.YES_OPTION){System.exit(0);}
			}
		});
	}

	public void actionPerformed(ActionEvent e)
	{
		switch (Integer.parseInt(e.getActionCommand()))
		{
			case 0:
                frame.remove(panel);
				origin_panel();
				break;
			case 1:
				log_in();
				break;
			case 2:
				sign_up();
				break;
			default:
				break;
		}
		if(comboBox!=null)
		{
			comboBox.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					id=(String) comboBox.getSelectedItem();
				}
			});
		}
	}
	public void origin_panel()
	{
		panel= new JPanel();

		JButton log_in=new JButton("登入");
		JButton sign_up=new JButton("註冊");
		log_in.setActionCommand("1");
		sign_up.setActionCommand("2");
		log_in.addActionListener(this);
		sign_up.addActionListener(this);

		panel.add(log_in);
		panel.add(sign_up);
		frame.add(panel);
		frame.setVisible(true);
	}
	public void sign_up()
	{
		frame.remove(panel);
		panel= new JPanel();
		panel2= new JPanel();
		panel3= new JPanel();
		panel2.setLayout(new GridLayout(4,2));
		comboBox = new JComboBox<>();
		accTF=new JTextField(16);
		nameTF=new JTextField(16);
		passTF=new JPasswordField(16); // 非明文密碼輸入；
		comboBox.addItem("請選擇身分");
		comboBox.addItem("學生");
        	comboBox.addItem("教師");
		comboBox.addItem("職員");
		comboBox.addItem("管理員");

		JLabel idenLB = new JLabel("身分");
		panel2.add(accLB);
		panel2.add(accTF);
		panel2.add(passLB);
		panel2.add(passTF);
		panel2.add(nameLB);
		panel2.add(nameTF);
		panel2.add(idenLB);
		panel2.add(comboBox);

        confirm=new JButton("確認");
		confirm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                password=new String(passTF.getPassword());
                user=new User(accTF.getText(),password,nameTF.getText(),id);
				if(check_signUp_Input()){user.sign_up();id_Pane(check_id());}
			    else{sign_up();}
            }
        });
        back=new JButton("返回");
		back.setActionCommand("0");
		back.addActionListener(this);
		
		panel.add(panel2);
		panel3.add(confirm);
		panel3.add(back);
		panel.add(panel3);
		frame.add(panel);
		frame.setVisible(true);
	}

	public void log_in()
	{
		frame.remove(panel);
		panel= new JPanel();
		panel2= new JPanel();
		panel2.setLayout(new GridLayout(2,2));

		accTF = new JTextField(16);
		passTF = new JPasswordField(16); // 非明文密碼輸入；
		
		panel2.add(accLB);
		panel2.add(accTF);
		panel2.add(passLB);
		panel2.add(passTF);

        confirm=new JButton("確認");
        confirm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                password=new String(passTF.getPassword());
                user=new User(accTF.getText(),password);
				if(check_login_Input())
                {
					id=user.getIdentity();
					JOptionPane.showMessageDialog(null, "登入成功\n"+user.getName()+" 歡迎回來~");
                    id_Pane(check_id());
                }
			    else{log_in();}
            }
        });
        back=new JButton("返回");
		back.setActionCommand("0");
		back.addActionListener(this);
		
		panel.add(panel2);
        	panel.add(confirm);
		panel.add(back);
		frame.add(panel);
		frame.setVisible(true);
	}
    
    public void id_Pane(int e)
    {
        
        switch(e)
        {
			case 0:
				frame.dispose();
                Student_UI student_UI=new Student_UI(user);
                student_UI.Home();
				break;
			case 1:
				frame.dispose();
                Teacher_UI teacher_UI=new Teacher_UI(user);
                teacher_UI.Home();
				break;
			case 2:
				frame.dispose();
                Staff_UI staff_UI=new Staff_UI(user);
                staff_UI.Home();
				break;
            case 3:
                frame.dispose();
                Admin_UI admin_UI=new Admin_UI(user);
                admin_UI.Home();
                break;
            default:
				frame.remove(panel);
                origin_panel();
                break;
        }
    }

    public boolean check_signUp_Input()
    {
        if(accTF.getText().isBlank()||nameTF.getText().isBlank()||password.isBlank()||id.isBlank()){JOptionPane.showMessageDialog(null, "輸入值不能為空!","警告",2);return false;}
        else if(user.find_Account()){JOptionPane.showMessageDialog(null,"此帳號已被註冊!","錯誤",0);return false;}
        else{return check_length();}
    }
    public boolean check_login_Input()
    {
        if(accTF.getText().isBlank()||password.isBlank()){JOptionPane.showMessageDialog(null, "輸入值不能為空!","警告",2);return false;}
        else if(user.find_Account()==false){JOptionPane.showMessageDialog(null,"此帳號不存在!","錯誤",0);return false;}
        else{return user.check_password();}
    }
    public int check_id()
	{
		if(id.equals("學生")){return 0;}
		else if(id.equals("教師")){return 1;}
		else if(id.equals("職員")){return 2;}
		else if(id.equals("管理員")){return 3;}
		else{JOptionPane.showMessageDialog(null, "請選擇項目","警告",2);return 4;}
	}
    public boolean check_length()
	{
		if(accTF.getText().length()<9 ||accTF.getText().length()>16){JOptionPane.showMessageDialog(null, "帳號長度需介於9~16!","警告",2);return false;}
        else if(password.length()<4||password.length()>12){JOptionPane.showMessageDialog(null, "密碼長度需介於4~12!","警告",2);return false;}
        else{return true;}
	}
}
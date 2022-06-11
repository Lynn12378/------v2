import java.awt.event.*;
import javax.swing.*;

import java.awt.*;

public class User_Interface implements ActionListener
{
	JFrame frame;
	JPanel panel,panel2,panel3;
	JComboBox<String> comboBox;
	JLabel accLB=new JLabel("�b��");
	JLabel passLB=new JLabel("�K�X");
	JLabel nameLB=new JLabel("�m�W");
	JLabel idLB=new JLabel("����");
    JButton confirm=new JButton("�T�{");
    JButton back=new JButton("��^");
	private JTextField accTF,nameTF;
	private JPasswordField passTF;

	protected User user;
	private String password,id;
	public int w=700,h=650;

	public static void main(String[] args)
	{
		User_Interface ui=new User_Interface();
        ui.origin_panel();
	}

	public User_Interface()
	{
		frame = new JFrame("�Ϯ��]");
		frame.setResizable(false);
		frame.setSize(w,h);
		frame.setLocationRelativeTo(null);//�m���}��(�����q���W�}��)
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				int result = JOptionPane.showConfirmDialog(frame,
						"�O�_�����{��",
						"ĵ�i",
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

		JButton log_in=new JButton("�n�J");
		JButton sign_up=new JButton("���U");
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
		passTF=new JPasswordField(16); // �D����K�X��J�F
		comboBox.addItem("�п�ܨ���");
		comboBox.addItem("�ǥ�");
        	comboBox.addItem("�Юv");
		comboBox.addItem("¾��");
		comboBox.addItem("�޲z��");

		JLabel idenLB = new JLabel("����");
		panel2.add(accLB);
		panel2.add(accTF);
		panel2.add(passLB);
		panel2.add(passTF);
		panel2.add(nameLB);
		panel2.add(nameTF);
		panel2.add(idenLB);
		panel2.add(comboBox);

        confirm=new JButton("�T�{");
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
        back=new JButton("��^");
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
		passTF = new JPasswordField(16); // �D����K�X��J�F
		
		panel2.add(accLB);
		panel2.add(accTF);
		panel2.add(passLB);
		panel2.add(passTF);

        confirm=new JButton("�T�{");
        confirm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                password=new String(passTF.getPassword());
                user=new User(accTF.getText(),password);
				if(check_login_Input())
                {
					id=user.getIdentity();
					JOptionPane.showMessageDialog(null, "�n�J���\\n"+user.getName()+" �w��^��~");
                    id_Pane(check_id());
                }
			    else{log_in();}
            }
        });
        back=new JButton("��^");
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
            /*case 3:
                frame.dispose();
                Admin_UI admin_UI=new Admin_UI(user);
                admin_UI.Home();
                break; */
            default:
                origin_panel();
                break;
        }
    }

    public boolean check_signUp_Input()
    {
        if(accTF.getText().isBlank()||nameTF.getText().isBlank()||password.isBlank()||id.isBlank()){JOptionPane.showMessageDialog(null, "��J�Ȥ��ର��!","ĵ�i",2);return false;}
        else if(user.find_Account()){JOptionPane.showMessageDialog(null,"���b���w�Q���U!","���~",0);return false;}
        else{return check_length();}
    }
    public boolean check_login_Input()
    {
        if(accTF.getText().isBlank()||password.isBlank()){JOptionPane.showMessageDialog(null, "��J�Ȥ��ର��!","ĵ�i",2);return false;}
        else if(user.find_Account()==false){JOptionPane.showMessageDialog(null,"���b�����s�b!","���~",0);return false;}
        else{return user.check_password();}
    }
    public int check_id()
	{
		if(id.equals("�ǥ�")){return 0;}
		else if(id.equals("�Юv")){return 1;}
		else if(id.equals("¾��")){return 2;}
		else if(id.equals("�޲z��")){return 3;}
		else{JOptionPane.showMessageDialog(null, "�п�ܶ���","ĵ�i",2);return 4;}
	}
    public boolean check_length()
	{
		if(accTF.getText().length()<9 ||accTF.getText().length()>16){JOptionPane.showMessageDialog(null, "�b�����׻ݤ���9~16!","ĵ�i",2);return false;}
        else if(password.length()<4||password.length()>12){JOptionPane.showMessageDialog(null, "�K�X���׻ݤ���4~12!","ĵ�i",2);return false;}
        else{return true;}
	}
}
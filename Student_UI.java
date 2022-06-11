import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
public class Student_UI  extends User_Interface
{
    JScrollPane jScrollPane;
    JButton button;
    private JTextField nameTF,passTF;
    private JTextField ISBNTF,titleTF,authorTF,publisherTF;
    private JLabel ISBNLB=new JLabel("ISBN:");
    private JLabel titleLB=new JLabel("�ѦW:");
    private JLabel authorLB=new JLabel("�@��:");
    private JLabel publisherLB=new JLabel("�X����:");
    private String title,author,publisher,ISBN;
    private ArrayList<Book> books;

	public int w=1000,h=750;

    public Student_UI (User user)
    {
        super();
        setstudent(user);
        passTF=new JTextField(user.getPassword());
        nameTF=new JTextField(user.getName());
    }
    public void actionPerformed(ActionEvent e)
	{
		switch (Integer.parseInt(e.getActionCommand()))
		{
			case 0:
                frame.remove(panel);
				Home();
				break;
			case 1:
                sear_Book();
				break;
			case 2:
                return_Book();
			   break;
            case 3:
                inform();
                break;
            case 4:
                history();
                break;
			default:
				break;
		}
	}
    public void Home()
    {
		panel= new JPanel();

		JButton sear_Book=new JButton("���y�d��");
		JButton return_Book=new JButton("�ٮ�");
		JButton inform=new JButton("�ӤH��T");
		JButton history=new JButton("���v����");

		sear_Book.setActionCommand("1");
		return_Book.setActionCommand("2");
		inform.setActionCommand("3");
		history.setActionCommand("4");

		sear_Book.addActionListener(this);
		return_Book.addActionListener(this);
		inform.addActionListener(this);
		history.addActionListener(this);

		panel.add(sear_Book);
		panel.add(return_Book);
		panel.add(inform);
		panel.add(history);

        frame.add(panel);
        frame.setVisible(true);
    }
    public void sear_Book()
    {
        frame.remove(panel);
		panel= new JPanel();

        ISBNTF=new JTextField(16);
        titleTF=new JTextField(16);
        authorTF=new JTextField(16);
        publisherTF=new JTextField(16);

        confirm=new JButton("�T�w");
		confirm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                title=titleTF.getText().isBlank()?"\t":titleTF.getText();
                author=authorTF.getText().isBlank()?"\t":authorTF.getText();
                publisher=publisherTF.getText().isBlank()?"\t":publisherTF.getText();
                ISBN=ISBNTF.getText().isBlank()?"\t":ISBNTF.getText();
                
                if(titleTF.getText().equals("\t")&&authorTF.getText().equals("\t")&&publisherTF.getText().equals("\t")&&ISBNTF.getText().equals("\t"))
                {
                    JOptionPane.showMessageDialog(null, authorTF.getText().toString()+"��J�Ȥ��ର��!","ĵ�i",2);
                    sear_Book();
                }
                else
                {
                    Seach_Book seach_Book=new Seach_Book(title, author, publisher, ISBN);
                    books=seach_Book.getBook();
                    if(books.toString()=="[]"){JOptionPane.showMessageDialog(null, "�d�L���!","���~",0);sear_Book();}
                    else{sear_result(books);}
                }
            }
        });
        back=new JButton("��^");
        back.setActionCommand("0");
        back.addActionListener(this);
        panel.add(titleLB);
        panel.add(titleTF);
		panel.add(authorLB);
        panel.add(authorTF);
		panel.add(publisherLB);
        panel.add(publisherTF);
		panel.add(ISBNLB);
        panel.add(ISBNTF);
		panel.add(confirm);
		panel.add(back);
		frame.add(panel);
		frame.setVisible(true);
    }
    public void sear_result(ArrayList<Book> books)
    {
        frame.remove(panel);
		panel= new JPanel();
        frame.setLayout(new GridLayout(5,5));//�ƪ����ɭԭn�R��
        for(int i=0;i<books.size();i++)
        {
            panel.add(search_pane(books.get(i),i));
        }
        back=new JButton("��^");
        back.setActionCommand("1");
        back.addActionListener(this);

        panel.add(titleLB);
		panel.add(authorLB);
		panel.add(publisherLB);
		panel.add(ISBNLB);
		panel.add(back);

        
		frame.add(panel);
		frame.setVisible(true);
    }
    public JPanel search_pane(Book book,int i)
    {
        JPanel pane=new JPanel();
        
        JLabel ISBNLB2=new JLabel(book.getISBN());
        JLabel titleLB2=new JLabel(book.getTitle());
        JLabel authorLB2=new JLabel(book.getAuthor());
        JLabel publisherLB2=new JLabel(book.getPublisher());

        if(book.getState().equals("in_Library"))
        {
            button=new JButton("�ɮ�");
            button.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    JOptionPane.showMessageDialog(null,"�ɮѧ���");
                    user.borrowBook(book);
                    sear_Book();
                }
            });
        }
        else if(book.getState().equals(user.getAccount())){button=new JButton("�ɮѤ�");button.setEnabled(false);}
        else{button=new JButton("�w�~��");button.setEnabled(false);}
        pane.add(titleLB2);
        pane.add(authorLB2);
        pane.add(publisherLB2);
        pane.add(ISBNLB2);
        pane.add(button);
        return pane;
    }
    public void return_Book()
    {
        frame.remove(panel);
		panel= new JPanel();
        JLabel dueLB=new JLabel("�����:");
        frame.setLayout(new GridLayout(5,5));//�ƪ����ɭԭn�R��
        books=new ArrayList<>();
        books=user.find_Book_borrowed();
        for(int i=0;i<books.size();i++)
        {
            panel.add(return_pane(books.get(i)));
        }
        back=new JButton("��^");
        back.setActionCommand("0");
        back.addActionListener(this);

        panel.add(titleLB);
		panel.add(authorLB);
		panel.add(publisherLB);
		panel.add(ISBNLB);
        panel.add(dueLB);
		panel.add(back);

        
		frame.add(panel);
		frame.setVisible(true);
    }
    public JPanel return_pane(Book book)
    {
        JPanel pane=new JPanel();
                JLabel ISBNLB2=new JLabel(book.getISBN());
        JLabel titleLB2=new JLabel(book.getTitle());
        JLabel authorLB2=new JLabel(book.getAuthor());
        JLabel publisherLB2=new JLabel(book.getPublisher());
        //JLabel dueLB2=new JLabel(user.get_Due(book.getBorrowDate()),user.getDay());
        
        if(book.getState().equals(user.getAccount()))
        {
            button=new JButton("�ٮ�");
            button.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    user.returnBook(book);
                    JOptionPane.showMessageDialog(null,"�k�٧���");
                    return_Book();
                }
            });
        }
        pane.add(titleLB2);
        pane.add(authorLB2);
        pane.add(publisherLB2);
        pane.add(ISBNLB2);
        //pane.add(dueLB2);
        pane.add(button);
        return pane;
    }
    public void inform()
    {
		frame.remove(panel);
		panel= new JPanel();
		JButton sign_out = new JButton("�n�X");
		JButton update = new JButton("�ק���");
		back = new JButton("��^");

		JLabel accLB2,nameLB2,passLB2,idLB2;
		accLB2=new JLabel(user.getAccount());
		nameLB2=new JLabel(user.getName());
		passLB2=new JLabel(user.getPassword());
		idLB2=new JLabel(user.getIdentity());
        
		JLabel quanLB=new JLabel("�ɮѤW��:"+Integer.toString(user.getQuantity()-(user.find_Book_borrowed().size()))+"/"+Integer.toString(user.getQuantity()));
        JLabel finesLB=new JLabel("��ú�@��:"+user.getFines());

		panel.add(accLB);
		panel.add(accLB2);
		panel.add(nameLB);
		panel.add(nameLB2);
		panel.add(passLB);
		panel.add(passLB2);
		panel.add(idLB);
		panel.add(idLB2);
		panel.add(quanLB);
		panel.add(finesLB);

		back.setActionCommand("0");
		back.addActionListener(this);

        sign_out.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                User_Interface user_Interface=new User_Interface();
                user_Interface.origin_panel();
            };
        });
        update.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               update();
            }
        });

        panel.add(sign_out);
		panel.add(update);
		panel.add(back);
		frame.add(panel);
		frame.setVisible(true);
    }
    public void update()
    {   
        frame.remove(panel);
        panel=new JPanel();

        back=new JButton("��^");
        back.setActionCommand("3");
        back.addActionListener(this);

        confirm=new JButton("�T�w");
        confirm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(check_length())
                {
                    user.reviseName(nameTF.getText());
                    user.revisePassword(passTF.getText());
                    user.reviseuser_Data(user);
                    inform();
                }
                else{update();}
            }
            public boolean check_length()
            {
                if(passTF.getText().length()<4||passTF.getText().length()>12){JOptionPane.showMessageDialog(null, "�K�X���׻ݤ���4~12!","ĵ�i",3);return false;}
                else{return true;}
            }
        });

        panel.add(confirm);
        panel.add(back);
        panel.add(nameLB);
        panel.add(nameTF);
		panel.add(passLB);
        panel.add(passTF);
        frame.add(panel);
        frame.setVisible(true);
    } 
    public void history()
    {
        frame.remove(panel);
		panel= new JPanel();
        frame.setLayout(new GridLayout(5,5));//�ƪ����ɭԭn�R��
        JLabel periodLB=new JLabel("�ɮѴ���:");

        ArrayList<Book> books=new ArrayList<>();
        books=user.get_Record_book();
        for(int i=0;i<books.size();i++)
        {
            panel.add(history_pane(books.get(i)));
        }
        back=new JButton("��^");
        back.setActionCommand("0");
        back.addActionListener(this);

        panel.add(titleLB);
		panel.add(authorLB);
		panel.add(publisherLB);
		panel.add(ISBNLB);
        panel.add(periodLB);
		panel.add(back);

		frame.add(panel);
		frame.setVisible(true);
    }
    public JPanel history_pane(Book book)
    {
        JPanel pane=new JPanel();

        JLabel ISBNLB2=new JLabel(book.getISBN());
        JLabel titleLB2=new JLabel(book.getTitle());
        JLabel authorLB2=new JLabel(book.getAuthor());
        JLabel publisherLB2=new JLabel(book.getPublisher());
        JLabel periodLB2=new JLabel(user.get_Due(book));
        pane.add(titleLB2);
        pane.add(authorLB2);
        pane.add(publisherLB2);
        pane.add(ISBNLB2);
        panel.add(periodLB2);

        return pane;
    }
    public void setstudent(User user){this.user=new Student(user.getAccount(), user.getPassword(), user.getName(),user.getIdentity(),Integer.toString(user.getFines()));}
}
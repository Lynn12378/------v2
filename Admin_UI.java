import javax.swing.*;
import java.awt.event.*;

public class Admin_UI  extends Member_UI
{
    private Admin admin;
    public Admin_UI (User user)
    {
        super(user);
        setID(user);
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
            case 5:
                manage_Book();
                break;
            case 6:
                payFines();
                break;
			default:
				break;
		}
	}
    public void Home()
    {
		panel= new JPanel();

		JButton sear_Book=new JButton("書籍查詢");
		JButton return_Book=new JButton("還書");
		JButton inform=new JButton("個人資訊");
		JButton history=new JButton("歷史紀錄");
        JButton manage=new JButton("管理書籍");
		JButton payfine=new JButton("繳納罰金");

		sear_Book.setActionCommand("1");
		return_Book.setActionCommand("2");
		inform.setActionCommand("3");
		history.setActionCommand("4");
        manage.setActionCommand("5");
        payfine.setActionCommand("6");

		sear_Book.addActionListener(this);
		return_Book.addActionListener(this);
		inform.addActionListener(this);
		history.addActionListener(this);
        manage.addActionListener(this);
		payfine.addActionListener(this);

		panel.add(sear_Book);
		panel.add(return_Book);
		panel.add(inform);
		panel.add(history);
        panel.add(manage);
        panel.add(payfine);

        frame.add(panel);
        frame.setVisible(true);
    }
    public void manage_Book()
    {   
        frame.remove(panel);
        panel=new JPanel();

        back=new JButton("返回");
        back.setActionCommand("0");
        back.addActionListener(this);

        JButton add=new JButton("新增書籍");
        add.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                addBook();
            }
        });
        JButton remove=new JButton("刪除書籍");
        remove.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                index_removeBook();
            }
        });
        JButton revise=new JButton("修改書籍");
        revise.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                index_reviseBook();
            }
        });

        panel.add(add);
        panel.add(remove);
        panel.add(revise);
        panel.add(back);

        frame.add(panel);
        frame.setVisible(true);
    }
    public void addBook()
	{
		frame.remove(panel);
		panel= new JPanel();

        ISBNTF=new JTextField(16);
        titleTF=new JTextField(16);
        authorTF=new JTextField(16);
        publisherTF=new JTextField(16);

		panel.add(titleLB);
        panel.add(titleTF);
		panel.add(authorLB);
        panel.add(authorTF);
		panel.add(publisherLB);
        panel.add(publisherTF);
		panel.add(ISBNLB);
        panel.add(ISBNTF);

        confirm=new JButton("確定");
		confirm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(admin.addBook(ISBNTF.getText(), titleTF.getText(), authorTF.getText(), publisherTF.getText())){manage_Book();}
                else{addBook();addBook();}
            }
        });
        back=new JButton("返回");
		back.setActionCommand("5");
		back.addActionListener(this);

		panel.add(confirm);
		panel.add(back);
		frame.add(panel);
		frame.setVisible(true);
	}
    public void index_removeBook()
	{
		frame.remove(panel);
		panel= new JPanel();

        ISBNTF=new JTextField(16);

		panel.add(ISBNLB);
        panel.add(ISBNTF);

        confirm=new JButton("確定");
		confirm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(user.findISBN(ISBNTF.getText())==-1){JOptionPane.showMessageDialog(null,"查無此書!","錯誤",0);index_removeBook();}
                else{removeBook();}
            }
        });
        back=new JButton("返回");
		back.setActionCommand("5");
		back.addActionListener(this);

		panel.add(confirm);
		panel.add(back);
		frame.add(panel);
		frame.setVisible(true);
	}
    public void removeBook()
	{
		frame.remove(panel);
		panel= new JPanel();
        book=user.getBook(ISBNTF.getText());
        JLabel ISBNLB2=new JLabel(book.getISBN());
        JLabel titleLB2=new JLabel(book.getTitle());
        JLabel authorLB2=new JLabel(book.getAuthor());
        JLabel publisherLB2=new JLabel(book.getPublisher());


        confirm=new JButton("確定");
		confirm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                admin.removeBook(ISBNTF.getText());
                index_removeBook();
            }
        });
        back=new JButton("返回");
		back.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                index_removeBook();
            }
        });

        panel.add(titleLB);
        panel.add(titleLB2);
		panel.add(authorLB);
        panel.add(authorLB2);
		panel.add(publisherLB);
        panel.add(publisherLB2);
		panel.add(ISBNLB);
        panel.add(ISBNLB2);
		panel.add(confirm);
		panel.add(back);
		frame.add(panel);
		frame.setVisible(true);
	}
    public void index_reviseBook()
	{
		frame.remove(panel);
		panel= new JPanel();

        ISBNTF=new JTextField(16);

		panel.add(ISBNLB);
        panel.add(ISBNTF);

        confirm=new JButton("確定");
		confirm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(user.findISBN(ISBNTF.getText())==-1){JOptionPane.showMessageDialog(null,"查無此書!","錯誤",0);index_reviseBook();}
                else{reviseBook();}
            }
        });
        back=new JButton("返回");
		back.setActionCommand("5");
		back.addActionListener(this);

		panel.add(confirm);
		panel.add(back);
		frame.add(panel);
		frame.setVisible(true);
	}
    public void reviseBook()
	{
        frame.remove(panel);
		panel= new JPanel();
        book=user.getBook(ISBNTF.getText());
        ISBNTF=new JTextField(book.getISBN());
        titleTF=new JTextField(book.getTitle());
        authorTF=new JTextField(book.getAuthor());
        publisherTF=new JTextField(book.getPublisher());

        confirm=new JButton("確定");
		confirm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(check_length())
                {
                    book.revisetTitle(titleTF.getText());
                    book.revisetAuthor(authorTF.getText());
                    book.revisePublisher(publisherTF.getText());
                    admin.reviseBook(book);
                    index_reviseBook();
                }
                else{reviseBook();}
            }
            public boolean check_length()
            {
                if(ISBNTF.getText().length()!=8){JOptionPane.showMessageDialog(null, "ISBN長度需等於8!","警告",0);return false;}
                else{return true;}
            }
        });

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
    public void payFines()
	{
		frame.remove(panel);
		panel=new JPanel();

        accTF=new JTextField(16);
        JLabel fineLB=new JLabel("罰金:");
        JTextField fineTF=new JTextField(16);

		panel.add(accLB);
        panel.add(accTF);
        panel.add(fineLB);
        panel.add(fineTF);


        confirm=new JButton("確定");
		confirm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(accTF.getText().isBlank()||fineTF.getText().isBlank()){JOptionPane.showMessageDialog(null, "尚無罰金");payFines();}
                else if(admin.payFines(accTF.getText(),Integer.parseInt(fineTF.getText()))){frame.remove(panel);Home();}
                else{payFines();}
            }
        });
        back=new JButton("返回");
		back.setActionCommand("0");
		back.addActionListener(this);

		panel.add(confirm);
		panel.add(back);
		frame.add(panel);
		frame.setVisible(true);
	}

    public void setID(User user)
    {
        admin=new Admin(user.getAccount(), user.getPassword(), user.getName(),user.getIdentity(),Integer.toString(user.getFines()));
        this.user=admin;
    }
    public int getDay(){return admin.getDay();}
}
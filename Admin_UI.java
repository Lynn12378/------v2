import javax.swing.*;
public class Admin_UI  extends Member_UI
    public Admin_UI (User user)
    {
        super(user);
    }
    public void setID(User user){this.user=new Admin(user.getAccount(), user.getPassword(), user.getName(),user.getIdentity(),Integer.toString(user.getFines()));}

    public void manage_Book()
    {   
        frame.remove(panel);
        panel=new JPanel();

        back=new JButton("��^");
        back.setActionCommand("0");
        back.addActionListener(this);

        JButton add=new JButton("�s�W���y");
        add.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                addBook();
            }
        });
        JButton remove=new JButton("�R�����y");
        remove.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                index_removeBook();
            }
        });
        JButton revise=new JButton("�ק���y");
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

        confirm=new JButton("�T�{");
		confirm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(admin.addBook(ISBNTF.getText(), titleTF.getText(), authorTF.getText(), publisherTF.getText())){manage_Book();}
                else{addBook();index_removeBook();}
            }
        });
        back=new JButton("��^");
		back.setActionCommand("4");
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

        confirm=new JButton("�T�{");
		confirm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(user.findISBN(ISBNTF.getText())==-1){JOptionPane.showMessageDialog(null,"�d�L����!","���~",0);index_removeBook();}
                else{removeBook();}
            }
        });
        back=new JButton("��^");
		back.setActionCommand("4");
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


        confirm=new JButton("�T�{");
		confirm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                admin.removeBook(ISBNTF.getText());
                index_removeBook();
            }
        });
        back=new JButton("��^");
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

        confirm=new JButton("�T�{");
		confirm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(user.findISBN(ISBNTF.getText())==-1){JOptionPane.showMessageDialog(null,"�d�L����!","���~",0);index_reviseBook();}
                else{reviseBook();}
            }
        });
        back=new JButton("��^");
		back.setActionCommand("4");
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

        confirm=new JButton("�T�{");
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
                if(ISBNTF.getText().length()!=12){JOptionPane.showMessageDialog(null, "ISBN���׻ݵ���12!","ĵ�i",2);return false;}
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
    public void setstudent(User user){this.user=new Student(user.getAccount(), user.getPassword(), user.getName(),user.getIdentity(),Integer.toString(user.getFines()));}
}
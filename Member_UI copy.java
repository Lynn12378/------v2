import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
public abstract class Member_UI  extends User_Interface
{
    JScrollPane jScrollPane;
    JButton button;

    protected Book book;
    protected JTextField nameTF,passTF;
    protected JTextField ISBNTF,titleTF,authorTF,publisherTF;
    protected JLabel ISBNLB=new JLabel("ISBN:");
    protected JLabel titleLB=new JLabel("書名:");
    protected JLabel authorLB=new JLabel("作者:");
    protected JLabel publisherLB=new JLabel("出版商:");
    protected String title,author,publisher,ISBN;
    protected ArrayList<Book> books;

	public int w=1000,h=750;

    public Member_UI (User user)
    {
        super();
        setID(user);
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

		JButton sear_Book=new JButton("書籍查詢");
		JButton return_Book=new JButton("還書");
		JButton inform=new JButton("個人資訊");
		JButton history=new JButton("歷史紀錄");

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

        confirm=new JButton("確定");
		confirm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                title=titleTF.getText().isBlank()?"\t":titleTF.getText();
                author=authorTF.getText().isBlank()?"\t":authorTF.getText();
                publisher=publisherTF.getText().isBlank()?"\t":publisherTF.getText();
                ISBN=ISBNTF.getText().isBlank()?"\t":ISBNTF.getText();
            
                if(titleTF.getText().isEmpty()&&authorTF.getText().isEmpty()&&publisherTF.getText().isEmpty()&&ISBNTF.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, authorTF.getText().toString()+"輸入值不能為空!","警告",2);
                    sear_Book();
                }
                else
                {
                    Seach_Book seach_Book=new Seach_Book(title, author, publisher, ISBN);
                    books=seach_Book.getBook();
                    if(books.toString()=="[]"){JOptionPane.showMessageDialog(null, "查無資料!","錯誤",0);sear_Book();}
                    else{sear_result(books);}
                }
            }
        });
        back=new JButton("返回");
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
        frame.setLayout(new GridLayout(5,5));//排版的時候要刪掉
        for(int i=0;i<books.size();i++)
        {
            panel.add(search_pane(books.get(i),i));
        }
        back=new JButton("返回");
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
            button=new JButton("借書");
            button.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    JOptionPane.showMessageDialog(null,"借書完成");
                    user.borrowBook(book);
                    sear_Book();
                }
            });
        }
        else if(book.getState().equals(user.getAccount())){button=new JButton("借書中");button.setEnabled(false);}
        else{button=new JButton("已外借");button.setEnabled(false);}
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
        JLabel dueLB=new JLabel("到期日:");
        frame.setLayout(new GridLayout(5,5));//排版的時候要刪掉
        books=new ArrayList<>();
        books=user.find_Book_borrowed();
        for(int i=0;i<books.size();i++)
        {
            panel.add(return_pane(books.get(i)));
        }
        back=new JButton("返回");
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
            button=new JButton("還書");
            button.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    user.returnBook(book);
                    JOptionPane.showMessageDialog(null,"歸還完成");
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
		JButton sign_out = new JButton("登出");
		JButton update = new JButton("修改資料");
		back = new JButton("返回");

		JLabel accLB2,nameLB2,passLB2,idLB2;
		accLB2=new JLabel(user.getAccount());
		nameLB2=new JLabel(user.getName());
		passLB2=new JLabel(user.getPassword());
		idLB2=new JLabel(user.getIdentity());
        
		JLabel quanLB=new JLabel("借書上限:"+Integer.toString(user.getQuantity()-(user.find_Book_borrowed().size()))+"/"+Integer.toString(user.getQuantity()));
        JLabel finesLB=new JLabel("需繳罰金:"+user.getFines());

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

        back=new JButton("返回");
        back.setActionCommand("3");
        back.addActionListener(this);

        confirm=new JButton("確定");
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
                if(passTF.getText().length()<4||passTF.getText().length()>12){JOptionPane.showMessageDialog(null, "密碼長度需介於4~12!","警告",3);return false;}
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
        frame.setLayout(new GridLayout(5,5));//排版的時候要刪掉
        JLabel periodLB=new JLabel("借書期間:");

        ArrayList<Book> books=new ArrayList<>();
        books=user.get_Record_book();
        for(int i=0;i<books.size();i++)
        {
            panel.add(history_pane(books.get(i)));
        }
        back=new JButton("返回");
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
    public abstract void setID(User user);
}
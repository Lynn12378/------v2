import java.util.ArrayList;
import javax.swing.JOptionPane;
public class User
{
	Time time;
	private User_Data user_Data=new User_Data();
	private Book_Data book_Data=new Book_Data();
	private Record_Data record_Data=new Record_Data();
	protected ArrayList<String> user=new ArrayList<String>();
	protected ArrayList<User> users=new ArrayList<User>();
	protected ArrayList<Book> booklist;
	protected ArrayList<Book> books;
	protected ArrayList<Record> record;
	protected ArrayList<Record> records;
	protected int accIndex;
	protected int ISBN_Index,record_Index;
	protected int rate,day,quantity;

	public User(String account,String password,String name,String identity){this(account,password,name,identity,0);}//註冊新帳號用，罰金==0
	public User(String account,String password){setAccount(account);setPassword(password);log_in();}//登入驗證帳密用
	public User(String account,String password,String name,String identity,int fines)//輸入用
	{
		setAccount(account);
		setPassword(password);
		setName(name);
		setIdentity(identity);
		setFines(fines);
	}
	public User(String account,String password,String name,String identity,String fines) //讀檔用
	{
		setAccount(account);
		setPassword(password);
		setName(name);
		setIdentity(identity);
		setFines(fines);
	}

	public int find_Account(String account)
	{
		int Index=-1;
		for(int i=0;i<user_Data.readUsers().size();i++){if(user_Data.readUsers().get(i).getAccount().equals(account)){Index=i;}}
		return Index;
	}
	public boolean find_Account()
	{
		accIndex=find_Account(getAccount());
		return accIndex==-1?false:true;
	}
	public void sign_up(){JOptionPane.showMessageDialog(null,"註冊完成~");user_Data.addUser(this);}
	public void log_in()
	{
		find_Account();
		setName(user_Data.readUsers().get(accIndex).getName());
		setIdentity(user_Data.readUsers().get(accIndex).getIdentity());
		setFines(user_Data.readUsers().get(accIndex).getFines());
	}
	public boolean check_password()
	{
		if(user_Data.readUsers().get(accIndex).getPassword().equals(getPassword())){return true;}
		else{JOptionPane.showMessageDialog(null, "密碼不正確!","警告",3);return false;}
	}
	public void reviseuser_Data(User user)
	{
		find_Account();
		users=user_Data.readUsers();
		users.set(accIndex,user);
		user_Data.rewriteUsers(users);
	}

	public int findISBN(String ISBN)
	{
		ISBN_Index=-1;
		for(int i=0;i<book_Data.readBooks().size();i++){if(book_Data.readBooks().get(i).getISBN().equals(ISBN)){ISBN_Index=i;break;}}
		return ISBN_Index;
	}
	public Book getBook(String ISBN){return book_Data.readBooks().get(ISBN_Index);}
	public void borrowBook(Book book)
	{
		if(find_Book_borrowed().size()>=getQuantity()){JOptionPane.showMessageDialog(null,"已達最高借書限制");}
		else
		{
			book.setState(getAccount());
			Time time=new Time();
			book.setBorrowDate(time.Day());
			books=book_Data.readBooks();
			books.set(findISBN(book.getISBN()),book);
			book_Data.rewriteBooks(books);
		}
	}
	public ArrayList<Book> find_Book_borrowed()
	{
		books=book_Data.readBooks();
		booklist=new ArrayList<>();
		for(int i=0;i<books.size();i++)
		{
			if(books.get(i).getState().equals(getAccount()))
			{
				booklist.add(books.get(i));
			}
		}
		return booklist;
	}
	public void returnBook(Book book)
	{
		time=new Time();
		int fines=calculate_fine(book.getBorrowDate(),time.Day());
		System.out.println(book.getBorrowDate());
		reviseFines(getFines()+fines);
		users=user_Data.readUsers();
		users.set(accIndex,this);
		user_Data.rewriteUsers(users);

		record_Data.addRecord(new Record(getAccount(),book.getISBN(),book.getBorrowDate()));

		book.reviseState("in_Library");
		book.reviseBorrowDate(null);
		books=book_Data.readBooks();
		books.set(findISBN(book.getISBN()),book);
		book_Data.rewriteBooks(books);
	}
	public int calculate_fine(String BD,String RD)
	{
		time=new Time();
		return (time.calculateDay(BD,RD)-getQuantity()>0)?time.calculateDay(BD,RD):0;
	}

	public ArrayList<Book> booklist(){return book_Data.readBooks();}
	public ArrayList<Book> mybooklist()
	{
		for(int i=0;i<book_Data.readBooks().size();i++)
		{
			if(book_Data.readBooks().get(i).getState().equals(getAccount()));{booklist.add(book_Data.readBooks().get(i));}
		}
		return booklist;
	}
	public String get_Due(String BD)
	{
		time=new Time();
		return time.dueDate(BD,getDay());
	}


	public ArrayList<Book> get_Record_books(String ISBN)
	{
		record_Data=new Record_Data();
        books=record_Data.readBooks();
        booklist=new ArrayList<>();
    
        for(int i=0;i<record.size();i++)
		{
			if(record.get(i).getAccount().equals(getAccount()))
			{
               
                booklist.add(books.get(i));
			}
		}
		return booklist;
	}
	public ArrayList<Record> get_Record()
	{
        record=record_Data.readRecords();
        records=new ArrayList<>();

        for(int i=0;i<record.size();i++)
		{
			if(record.get(i).getAccount().equals(getAccount()))
			{
                records.add(record.get(i));
			}
		}
		return records;
	}

	public String get_Due(Book book)
	{
		String period="";
		records=record_Data.readRecords();
		for(int i=0;i<records.size();i++)
		{
			if(records.get(i).getAccount().equals(getAccount()))
			{
				for(int j=0;j<books.size();j++)
				{
					if(books.get(j).getISBN().equals(records.get(i).getISBN()))
					{
						period=record_Data.readRecords().get(j).getBorrowDate()+"~"+record_Data.readRecords().get(j).getBorrowDate();
					}
				}
			}
		}
		return period;
	}

	public String toString()
	{
		String output="";
		for(int i=0;i<user.size();i++)
		{
			output+=user.get(i)+((i==user.size()-1)?"\n":"\t");
		}
		return output;
	}

	public void setAccount(String account){user.add(0,account);}
	public String getAccount(){return user.get(0);}

	public void setPassword(String password){user.add(1,password);}
	public String getPassword(){return user.get(1);}
	public void revisePassword(String password){user.set(1,password);}

	public void setName(String name){user.add(2,name);}
	public String getName(){return user.get(2);}
	public void reviseName(String name){user.set(2,name);}

	public void setIdentity(String identity){user.add(3,identity);}
	public String getIdentity(){return user.get(3);}

	public void setFines(int fines){user.add(4,Integer.toString(fines));}
	public void setFines(String fines){user.add(4,fines);}
	public int getFines(){return Integer.parseInt(user.get(4));}
	public void reviseFines(int fines){user.set(4,Integer.toString(fines));}

	public void setQuantity(){quantity=0;}
	public void setDay(){day=0;}
	public int getQuantity(){return quantity;}
	public int getDay(){return day;}
}
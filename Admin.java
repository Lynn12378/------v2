import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Admin extends Member
{
	private User_Data user_Data=new User_Data();
	private Book_Data book_Data=new Book_Data();
	private Record_Data record_Data=new Record_Data();
	private ArrayList<Book> books;

	public Admin(String account,String password,String name,String identity,String fines)
    {
        super(account,password,name,identity,fines);
        setQuantity();
        setDay();
    }
	public boolean addBook(String ISBN,String title,String author,String publisher)
	{
		Book book=new Book(ISBN,title,author,publisher,"in_Library");
		if(ISBN.isBlank()||title.isBlank()||author.isBlank()||publisher.isBlank()){JOptionPane.showMessageDialog(null, "輸入值不能為空!","警告",2);return false;}
		else if(findISBN(ISBN)!=-1){JOptionPane.showMessageDialog(null,"此書已經在書庫中!","錯誤",0);return false;}
		else if(book.getISBN().length()!=8){JOptionPane.showMessageDialog(null, "ISBN長度需等於8!","警告",2);return false;}
		else
		{
			record_Data.addBook(book);
			book_Data.addBook(book);
			JOptionPane.showMessageDialog(null, "書籍已新增");
			return true;
		}
	}
	public void removeBook(String ISBN)
	{
		findISBN(ISBN);
		books=book_Data.readBooks();
		books.remove(ISBN_Index);
		book_Data.rewriteBooks(books);
		JOptionPane.showMessageDialog(null, "書籍已刪除");
	}
	public void reviseBook(Book book)
	{
		findISBN(book.getISBN());
		books=book_Data.readBooks();
		books.set(ISBN_Index,book);
		book_Data.rewriteBooks(books);
		JOptionPane.showMessageDialog(null, "修改完成");
	}
	public boolean payFines(String account,int fine)
	{
		if(find_Account(account)==-1){JOptionPane.showMessageDialog(null,"查無帳號!","錯誤",0);return false;}
		else
		{
			User payer=user_Data.readUsers().get(find_Account(account));
			if(payer.getFines()==0){JOptionPane.showMessageDialog(null,"尚無罰金");return false;}
			else
			{	
				users=user_Data.readUsers();
				int re=payer.getFines()-fine;
				if(re<0){payer.setFines(re);}
				else{payer.setFines(re);}
				JOptionPane.showMessageDialog(null,"付款通過\n"+(re<0?"找零:"+(-re):"")+"目前尚未繳納罰金:"+payer.getFines());
				users.set(accIndex,payer);
				user_Data.rewriteUsers(users);
				return true;
			}	
		}
	}
	public void setQuantity(){quantity=12;}
	public void setDay(){day=21;}
    public int getQuantity(){return quantity;}
    public int getDay(){return day;}
}
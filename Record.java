import java.util.*;
import java.text.*;
public class Record
{
    private Record_Data record_Data=new Record_Data();
    private ArrayList<String> record=new ArrayList<>();
    private ArrayList<Book> books=new ArrayList<>();
    public Record(String account,String ISBN,String borrowDate,String returnDate)
    {
        setAccount(account);
        setISBN(ISBN);
        setBorrowDate(borrowDate);
        setReturnDate(returnDate);
    }
    public Record(String account,String ISBN,String borrowDate)
    {
        setAccount(account);
        setISBN(ISBN);
        setBorrowDate(borrowDate);
        setReturnDate();
    }
    public String toString()
	{
		String output="";
		for(int i=0;i<record.size();i++)
		{
			output+=record.get(i)+((i==record.size()-1)?"\n":"\t");
		}
		return output;
	}
    public void setReturnDate()
	{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
		record.add(3,sdf.format(date));
	}
    
    public void  setAccount(String account){record.add(0,account);}
    public void  setISBN(String ISBN){record.add(1,ISBN);}
    public void  setBorrowDate(String borrowDate){record.add(2,borrowDate);}
    public void  setReturnDate(String returnDate){record.add(3,returnDate);}
    public String  getAccount(){return record.get(0);}
    public String  getISBN(){return record.get(1);}
    public String  getBorrowDate(){return record.get(2);}
    public String  getReturnDate(){return record.get(3);}
}

import java.util.ArrayList;
import java.io.*;
public class Record_Data
{
    private ArrayList<Record> records;
	private ArrayList<Book> books;
	private String RecordTXT="Records.txt";
	private String BookTXT2="Books2.txt";

	public String input,output;
	public String[] inputArray=new String[4];
	public String[] inputArray2=new String[6];

	public void addRecord(Record record)
	{
		try{
			FileWriter fw=new FileWriter(RecordTXT,true);
			fw.write(record.toString());
			fw.flush();
        	fw.close();
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	public ArrayList<Record> readRecords()
	{
		try{
			FileReader fr = new FileReader(RecordTXT);
			BufferedReader br = new BufferedReader(fr);
			records=new ArrayList<>();
			while (br.ready())
			{
				input= br.readLine();
				inputArray=input.split("\t");
				records.add(new Record(inputArray[0],inputArray[1],inputArray[2],inputArray[3]));
			}
			fr.close();	
			return records;
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

	public void addBook(Book book)
	{
		try
		{
			FileWriter fw=new FileWriter(BookTXT2,true);
			fw.write(book.toString());
			fw.flush();
        	fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Book> readBooks()
	{
		try
		{
			FileReader fr = new FileReader(BookTXT2);
			BufferedReader br = new BufferedReader(fr);
			books=new ArrayList<>();
			while (br.ready())
			{
				input= br.readLine();
				inputArray2=input.split("\t");
				books.add(new Book(inputArray2[0],inputArray2[1],inputArray2[2],inputArray2[3],inputArray2[4],inputArray2[5]));
			}
			fr.close();	
			return books;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

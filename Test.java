import java.util.ArrayList;

public class Test
{
    public static void main(String [] args)
    {
        User user=new User("110403033","12345");
        Record_Data record_Data=new Record_Data();
        ArrayList<Record> record=record_Data.readRecords();
        ArrayList<Record> records=new ArrayList<>();
        
        
        for(int i=0;i<record.size();i++)
		{
		
            System.out.println(user.get_Record_books(user.get_Record().get(i).getISBN()).get(i));
        }
    }
}
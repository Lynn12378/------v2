import java.util.ArrayList;

public class Test
{
    public static void main(String [] args)
    {
        User user=new User("110403037","1111");
        Record_Data record_Data=new Record_Data();
        ArrayList<Record> record=record_Data.readRecords();
        ArrayList<Record> records=new ArrayList<>();
        
        System.out.println(user.get_Record());
        
    }
}
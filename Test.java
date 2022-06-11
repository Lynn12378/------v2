import java.util.ArrayList;

public class Test
{
    public static void main(String [] args)
    {
        User user=new User("110403037","1111");
        Admin admin=new Admin("110403037","1111","12","154564","0");
        Time time=new Time();
        /*Book book=new Book("20220613", "ll", "author", "publisher", "110403033","2022-05-10");

        Record_Data record_Data=new Record_Data();
        ArrayList<Record> record=record_Data.readRecords();
        ArrayList<Record> records=new ArrayList<>();
        
        System.out.println(time.calculateDay("2022-05-10","2022-06-12"));
        user.returnBook(book);*/
        System.out.println(time.dueDate("2022-05-20",admin.getDay()));
    }
}
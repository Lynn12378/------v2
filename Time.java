import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
 
public class Time
{
    public String Day()
    {
        Date date = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        return ft.format(date);
    }
   public int calculateDay(String BD,String RD)
   {
    int days=0;
        try{
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            Date bDate=ft.parse(BD);
            Date rDate=ft.parse(RD);
            
            days = (int) ((rDate.getTime() - bDate.getTime()) / (1000*3600*24));
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return days;
    }
    public String dueDate(String BD,int day)
    {
        String duedate="";
        try{
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            Date date=ft.parse(BD);
            Calendar c = Calendar.getInstance();
		    c.setTime(date);
		    c.add(Calendar.DATE, day);
		    Date date1=c.getTime();
            duedate=ft.format(date1);
            return duedate;
        }catch(Exception e){
            e.printStackTrace();
        }
        return duedate;
    }
}
public class Student_UI  extends Member_UI
{
    private Student student;
    public Student_UI (User user)
    {
        super(user);
        
    }
    public void setID(User user)
    {
        student=new Student(user.getAccount(),user.getPassword(),user.getName(),user.getIdentity(),Integer.toString(user.getFines()));
        this.user=student;
    }
    public int getDay(){return student.getDay();}
}
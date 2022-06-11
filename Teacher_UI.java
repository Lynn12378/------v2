public class Teacher_UI  extends Member_UI
{
    private Teacher teacher;
    public Teacher_UI (User user)
    {
        super(user);

    }
    public void setID(User user)
    {
        teacher=new Teacher(user.getAccount(),user.getPassword(),user.getName(),user.getIdentity(),Integer.toString(user.getFines()));
        this.user=teacher;
    }
    public int getDay(){return teacher.getDay();}
}
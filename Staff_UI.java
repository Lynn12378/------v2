public class Staff_UI  extends Member_UI
{
    private Staff staff;
    public Staff_UI (User user)
    {
        super(user);
    }
   
    public void setID(User user)
    {
        Staff staff=new Staff(user.getAccount(),user.getPassword(),user.getName(),user.getIdentity(),Integer.toString(user.getFines()));
        this.user=staff;
    }
    public int getDay(){return staff.getDay();}
}
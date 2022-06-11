public class Teacher extends Member
{
    public Teacher(String account,String password,String name,String identity,String fines)
    {
        super(account,password,name,identity,fines);
        setQuantity();
        setDay();
    }
    public void setQuantity(){quantity=15;}
	public void setDay(){day=30;}
    public int getQuantity(){return quantity;}
    public int getDay(){return day;}
}

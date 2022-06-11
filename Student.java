public class Student extends Member
{
    public Student(String account,String password,String name,String identity,String fines)
    {
        super(account,password,name,identity,fines);
        setQuantity();
        setDay();
    }
    
	public void setQuantity(){quantity=10;}
	public void setDay(){day=14;}
    public int getQuantity(){return quantity;}
    public int getDay(){return day;}
}

public class Staff extends Member
{   
    public Staff(String account,String password,String name,String identity,String fines)
    {
        super(account,password,name,identity,fines);
        setQuantity();
        setDay();
    }
    public void setQuantity(){quantity=5;}
	public void setDay(){day=14;}
    public int getQuantity(){return quantity;}
    public int getDay(){return day;}
}

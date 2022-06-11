public abstract class Member extends User
{
    public Member(String account,String password,String name,String identity,String fines){super(account,password,name,identity,fines);}
    public abstract void setQuantity();
    public abstract void setDay();
    public abstract int getQuantity();
    public abstract int getDay();
}
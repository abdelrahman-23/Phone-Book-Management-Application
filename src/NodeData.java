public class NodeData {
    public String dData,Name,FirstName,LastName,Address,EmailAddress,Phonenumber;
    public NodeData(String dData,String FirstName, String LastName, String phone, String email,String address)
    {
        this.dData = dData;
        this.Name = FirstName +" "+ LastName;
        this.FirstName = FirstName ;
        this.LastName = LastName ;
        this.Address = address;
        this.Phonenumber = phone;
        this.EmailAddress=email;
    }
    public void displayItem()
    {
        System.out.print(dData+" ");
    }
    @Override
    public String toString()
    {
        return "NodeData{" + "FirstName=" + FirstName + ", LastName=" + LastName + ", Address=" + Address + ", EmailAddress=" + EmailAddress + ", Phonenumber=" + Phonenumber + '}';
    }
}
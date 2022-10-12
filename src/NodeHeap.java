public class NodeHeap
{
    NodeHeap left ;
    NodeHeap right ;
    String key;
    String iData;
    String FirstName ;
    String LastName;
    String phone ;
    String email;
    String address;
    public NodeHeap()
    {
        iData = key;
        left=right=null;
    }
    public NodeHeap(String FirstName, String LastName, String email, String address, String phone)
    {
        this.FirstName=FirstName;
        this.LastName=LastName;
        this.address=address;
        this.email=email;
        this.phone=phone ;
    }
    public String getName()
    {
        return FirstName+" "+LastName;
    }
    @Override
    public String toString()
    {
        return " FirstName: "+FirstName+"\t LastName: "+LastName+"\t Email: "+email+"\t Address: "+address+"\t Phone: "+phone ;
    }
}
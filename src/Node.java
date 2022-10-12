public class Node
{
    private int height, count;
    private String element;
    private final String First_name;
    private final String last_name;
    private final String phone;
    private final String email;
    private final String address;
    private Node left, right;
    public Node(String element, String First_name, String last_name, String phone, String email, String address)
    {
        this.element = element;
        this.First_name = First_name;
        this.last_name = last_name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        height = 1;
        left = null;
        right = null;
        count = 1;
    }
    public int getCount()
    {
        return count;
    }
    public void setCount(int count)
    {
        this.count = count;
    }
    public int getHeight()
    {
        return height;
    }
    public void setHeight(int height)
    {
        this.height = height;
    }
    public String getElement()
    {
        return element;
    }
    public void setElement(Node element)
    {
        this.element = String.valueOf(element);
    }
    public String getline()
    {
        return First_name + " " +last_name + " " +phone + " " +email + " " +address;
    }
    public Node getLeft()
    {
        return left;
    }
    public void setLeft(Node left)
    {
        this.left = left;
    }
    public Node getRight()
    {
        return right;
    }
    public void setRight(Node right)
    {
        this.right = right;
    }
}
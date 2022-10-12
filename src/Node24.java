public class Node24
{
    private static final int ORDER = 4;
    private int numItems;
    private Node24 parent;
    private final Node24[] childArray = new Node24[ORDER];
    private final NodeData[] itemArray = new NodeData[ORDER - 1];
    public void connectChild(int childNum, Node24 child)
    {
        childArray[childNum] = child;
        if (child != null)
            child.parent = this;
    }
    public Node24 disconnectChild(int childNum)
    {
        Node24 tempNode24 = childArray[childNum];
        childArray[childNum] = null;
        return tempNode24;
    }
    public Node24 getChild(int childNum)
    {
        return childArray[childNum];
    }
    public Node24 getParent()
    {
        return parent;
    }
    public boolean isLeaf()
    {
        return childArray[0] == null;
    }
    public int getNumItems()
    {
        return numItems;
    }
    public void setNumItems(int theValue)
    {
        numItems = theValue;
    }
    public NodeData getItem(int index)
    {
        return itemArray[index];
    }
    public NodeData setItem(int index, NodeData theValue)
    {
        itemArray[index] = theValue;
        return itemArray[index];
    }

    public boolean isFull()
    {
        return numItems == ORDER - 1;
    }
    public int insertItem(NodeData newItem)
    {
        numItems++;
        String newKey = newItem.dData;
        for (int j = ORDER - 2; j >= 0; j--)
        {
            if (itemArray[j] != null)
            {
                String itsKey = itemArray[j].dData;
                if ((newKey.compareTo(itsKey))<0)
                    itemArray[j + 1] = itemArray[j];
                else
                {
                    itemArray[j + 1] = newItem;
                    return j + 1;
                }
            }
        }
        itemArray[0] = newItem;
        return 0;
    }
    public void insertatfront(NodeData newItem)
    {
        numItems++;
        for (int j = numItems - 1; j > 0; j--) {
            itemArray[j] = itemArray[j - 1];
            connectChild(j + 1, disconnectChild(j));
        }
        connectChild(1, disconnectChild(0));
        itemArray[0] = newItem;
        connectChild(0, null);
    }
    public NodeData removeItem()
    {
        NodeData temp = itemArray[numItems - 1];
        itemArray[numItems - 1] = null;
        numItems--;
        return temp;
    }
    public void displayNode()
    {
        for (int j = 0; j < numItems; j++)
            itemArray[j].displayItem();
    }
    public void displayvalue(int j)
    {
        itemArray[j].displayItem();
    }
    public void deletenodevalue(String theValue)
    {
        int flag = -1;
        for (int i = 0; i < numItems; i++) {
            if (theValue.compareTo(itemArray[i].dData)==0 )
            {
                flag = i;
            }
            if (flag != -1 && i + 1 < numItems)
            {
                itemArray[i].dData = itemArray[i + 1].dData;
            }
        }
        itemArray[numItems - 1] = null;
        numItems--;
    }
    public Node24 getsibiling(String theValue)
    {
        Node24 x = null;
        Node24 p = getParent();
        if (numItems != 0) {
            for (int i = 0; i <= p.numItems; i++)
            {
                if (p.childArray[i].itemArray[0].dData.compareTo(theValue)<0 )
                {
                    x = p.childArray[i];
                }
            }
        } else {
            for (int i = 0; i <= p.numItems; i++) {
                if (p.childArray[i].itemArray[0] == null) {
                    if (i != 0) {
                        x = p.childArray[i - 1];
                    }
                }
            }
        }
        return x;
    }
}
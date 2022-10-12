public class Heap
{
    private final NodeHeap[] heapData;
    private int sizeOfHeap;
    private final int heapMaxSize;
    public Heap(int heapMaxSize)
    {
        this.heapMaxSize = heapMaxSize;
        this.sizeOfHeap = 0;
        heapData = new NodeHeap[this.heapMaxSize + 1];
        heapData[0] = new NodeHeap();
    }
    private int getParentPosition(int position)
    {
        return position / 2;
    }
    private int getLeftChildPosition(int position)
    {
        return (2 * position);
    }
    private int getRightChildPosition(int position)
    {
        return (2 * position) + 1;
    }
    private boolean checkLeaf(int position)
    {
        return position >= (sizeOfHeap / 2) && position <= sizeOfHeap;
    }
    private void swap(int firstNode, int secondNode)
    {
        NodeHeap temp;
        temp = heapData[firstNode];
        heapData[firstNode] = heapData[secondNode];
        heapData[secondNode] = temp;
    }
    private void minHeapify(int position)
    {
        if (!checkLeaf(position))
        {
            if
            (heapData[position].getName().compareTo(heapData[getLeftChildPosition(position)].getName())>0 ||
                    heapData[position].getName().compareTo(heapData[getRightChildPosition(position)].getName())>0)
            {
                if
                (heapData[getLeftChildPosition(position)].getName().compareTo(heapData[getRightChildPosition(position)].getName())<0)
                {
                    swap(position, getLeftChildPosition(position));
                    minHeapify(getLeftChildPosition(position));
                }
                else
                {
                    swap(position, getRightChildPosition(position));
                    minHeapify(getRightChildPosition(position));
                }
            }
        }
    }
    public void insertNode(NodeHeap data)
    {
        if (sizeOfHeap>= heapMaxSize)
        {
            return;
        }
        heapData[++sizeOfHeap] = data;
        int current = sizeOfHeap;
        while
        (heapData[current].getName().compareTo(heapData[getParentPosition(current)].getName()) <0)
        {
            swap(current, getParentPosition(current));
            current = getParentPosition(current);
        }
    }
    public void displayHeap()
    {
        System.out.println(" PARENT NODE " + "\t" + " LEFT CHILD NODE " + "\t" +
                " RIGHT CHILD NODE ");
        for (int k = 1; k <= sizeOfHeap / 2; k++)
        {
            if (heapData[2 * k + 1]==null)
            {
                System.out.print(" " + heapData[k].getName() + "\t\t" + heapData[2 * k].getName() );
            }
            else
            {
                System.out.print(" " + heapData[k].getName() + "\t\t" +heapData[2 * k].getName() + "\t\t" + heapData[2 * k + 1].getName());
                System.out.println();
            }
        }
    }
    public void removeRoot()
    {
        deleteContact(heapData[1].getName());
    }
    public void search(String val)
    {
        boolean found=false;
        for(int i=0; i<=sizeOfHeap; i++)
        {
            if(heapData[i].getName().contains(val))
            {
                System.out.println("Contact found:");
                System.out.println(heapData[i]);
                System.out.println();
                found=true;
            }
        }
        if (!found)
        {
            System.out.println("Contact not found");
        }
    }
    private int getContactIndex(String ContactName)
    {
        for (int i = 1; i <= sizeOfHeap; i++)
        {
            if (heapData[i].getName().equals(ContactName))
                return i;
        }
        return -1;
    }
    public void deleteContact(String ContactName)
    {
        int index=getContactIndex(ContactName);
        if (index==(-1))
        {
            System.out.println("Contact: "+"\"" + ContactName + "\"" + " " +"is not found.");
            return;
        }
        swap(index, sizeOfHeap);
        NodeHeap Deleted = heapData[sizeOfHeap];
        heapData[sizeOfHeap] = null;
        minHeapify(index);
        sizeOfHeap--;
        System.out.println("Deleted Contact: \n "+Deleted);
        System.out.println();
    }
    public void inorderDisplay( int index)
    {
        if (heapData[index]==null)
            return;
        inorderDisplay(2*index);
        System.out.println(heapData[index]);
        System.out.println();
        inorderDisplay((2*index)+1);
    }
    public int height()
    {
        int p=1;
        int h=0;
        while(heapData[2*p] != null && 2*p<=heapData.length-1)
        {
            p*=2;
            h++;
        }
        return h;
    }
    public void printTreeInfo_hight()
    {
        System.out.println("Height: " + height());
    }
    public void printTreeInfo_node()
    {
        System.out.println("Number of nodes: "+ sizeOfHeap);
    }
}
public class TwoFour
{
    private Node24 root = new Node24();
    public void insert(int sort,String FirstName, String LastName, String phone, String email,String address)
    {
        Node24 curNode24 = root;
        String word = null;
        if (sort==0)
        {
            word=FirstName;
        }
        if (sort==1)
        {
            word=LastName;
        }
        if (sort==2)
        {
            word=FirstName+LastName;
        }
        NodeData tempItem = new NodeData(word,FirstName, LastName, phone, email,address);
        while (true)
        {
            if (curNode24.isFull())
            {
                split(curNode24);
                curNode24 = curNode24.getParent();
                curNode24 = getNextChild(curNode24, tempItem.dData);
            }
            else if (curNode24.isLeaf())
                break;
            else
                curNode24 = getNextChild(curNode24,tempItem.dData);
        }
        curNode24.insertItem(tempItem);
    }
    public void split(Node24 thisNode24)
    {
        NodeData itemB, itemC;
        Node24 parent, child2, child3;
        int itemIndex;
        itemC = thisNode24.removeItem();
        itemB = thisNode24.removeItem();
        child2 = thisNode24.disconnectChild(2);
        child3 = thisNode24.disconnectChild(3);
        Node24 newRight = new Node24();
        if (thisNode24 == root)
        {
            root = new Node24();
            parent = root;
            root.connectChild(0, thisNode24);
        }
        else
            parent = thisNode24.getParent();
        itemIndex = parent.insertItem(itemB);
        int n = parent.getNumItems();
        for (int j = n - 1; j > itemIndex; j--)
        {
            Node24 temp = parent.disconnectChild(j);
            parent.connectChild(j + 1, temp);
        }
        parent.connectChild(itemIndex + 1, newRight);
        newRight.insertItem(itemC);
        newRight.connectChild(0, child2);
        newRight.connectChild(1, child3);
    }
    public Node24 getNextChild(Node24 theNode24, String theValue)
    {
        int j;
        int numItems = theNode24.getNumItems();
        for (j = 0; j < numItems; j++)
        {
            if (theValue.compareTo( theNode24.getItem(j).dData)<0)
                return theNode24.getChild(j);
        }
        return theNode24.getChild(j);
    }
    public void displayTree(int i)
    {
        if (i == 0) {
            recDisplayTree(root, 0, 0);
        } else
            inorderdisplay(root, 0, 0);
        System.out.println();
    }
    private void recDisplayTree(Node24 thisNode24, int level, int childNumber)
    {
        System.out.print("level=" + level + " child=" + childNumber + " ");
        thisNode24.displayNode();
        System.out.println();
        hightlevel(level);
        int numItems = thisNode24.getNumItems();
        for (int j = 0; j < numItems + 1; j++) {
            Node24 nextNode24 = thisNode24.getChild(j);
            if (nextNode24 != null)
                recDisplayTree(nextNode24, level + 1, j);
            else
                return;
        }
    }


    public static void hightlevel(int lev){
        System.out.println("Height of tree is : "+lev);
    }
    public void inorderdisplay(Node24 thisNode24, int level, int childNumber)
    {
        int numItems = thisNode24.getNumItems();
        for (int j = 0; j < numItems + 1; j++) {
            Node24 nextNode24 = thisNode24.getChild(j);
            if (nextNode24 != null)
                inorderdisplay(nextNode24, level + 1, j);
            else {
                thisNode24.displayNode();
                return;
            }
            if (j < thisNode24.getNumItems())
                thisNode24.displayvalue(j);
        }
    }
    public Node24 find(String theValue)
    {
        return( findvalue(root, theValue));
    }
    public Node24 findvalue(Node24 theNode24, String theValue)
    {
        Node24 l = null;
        int numItems = theNode24.getNumItems();
        for (int j = 0; j < numItems; j++)
        {
            if (theValue.compareTo(theNode24.getItem(j).dData)==0
                    ||theValue.compareTo(theNode24.getItem(j).FirstName)==0
                    ||theValue.compareTo(theNode24.getItem(j).LastName)==0)
            {
                l = theNode24;
                System.out.println(theNode24.getItem(j).toString());
                break;
            }
            else if ((theValue.compareTo( theNode24.getItem(j).dData)<0 && !theNode24.isLeaf())
                    ||(theValue.compareTo( theNode24.getItem(j).FirstName)<0 && !theNode24.isLeaf())
                    ||(theValue.compareTo( theNode24.getItem(j).LastName)<0 && !theNode24.isLeaf())) {
                l = findvalue(theNode24.getChild(j), theValue);
                break;
            }
            else if ((theValue.compareTo(theNode24.getItem(j).dData)>0 && !theNode24.isLeaf())
                    ||(theValue.compareTo(theNode24.getItem(j).FirstName)>0 && !theNode24.isLeaf())
                    ||(theValue.compareTo(theNode24.getItem(j).LastName)>0 && !theNode24.isLeaf())) {
                l = findvalue(theNode24.getChild(j + 1), theValue);
            }
        }
        return l;
    }
    public Node24 delete(Node24 currnode, String theValue)
    {
        Node24 y ;
        if (currnode.isLeaf())
        {
            if (currnode.getNumItems() > 1)
            {
                currnode.deletenodevalue(theValue);
                return currnode;
            }
            else
            {
                y = deleteleaf_cases(currnode, theValue);
                return y;
            }
        }
        else
        {
            Node24 n = getNextChild(currnode, theValue);
            Node24 c = getinordernode(n);
            NodeData d = c.getItem(0);
            String k = d.dData;
            delete(c, d.dData);
            Node24 found = find(theValue);
            for(int i = 0; i < found.getNumItems();i++)
            {
                if(found.getItem(i).dData.compareTo(theValue)==0)
                {
                    found.getItem(i).dData=k;
                }
            }
            return found;
        }
    }
    public Node24 deleteleaf_cases(Node24 thisNode24, String theValue)
    {
        String sibling_side = "l";
        Node24 p = thisNode24.getParent();
        Node24 sibling = thisNode24.getsibiling(theValue);
        if (sibling == null)
        {
            sibling_side = "r";
            sibling = p.getChild(1);
        }
        if (sibling.getNumItems() == 1)
        {
            for (int i = 0; i <= p.getNumItems(); i++)
            {
                if (p.getChild(i) == sibling && sibling_side.equals("l"))
                {
                    thisNode24.setItem(thisNode24.getNumItems() - 1, null);
                    thisNode24.setNumItems(thisNode24.getNumItems() - 1);
                    NodeData d = p.getItem(i);
                    sibling.insertItem(d);
                    p.disconnectChild(i + 1);
                    for (int j = i; j < p.getNumItems(); j++)
                    {
                        if (j + 1 < p.getNumItems())
                        {
                            p.setItem(j, p.getItem(j + 1));
                            if (j + 2 <= p.getNumItems())
                            {
                                p.connectChild(j + 1, p.disconnectChild(j + 2));
                            }
                        }
                    }
                    p.setItem(p.getNumItems() - 1, null);
                    p.setNumItems(p.getNumItems() - 1);
                    if (p.getNumItems() == 0) {
                        if (p != root)
                        {
                            p = balancetree(p);
                        }
                        else
                        {
                            root = sibling;
                        }
                    }
                    return p;
                } else if (p.getChild(i) == sibling && sibling_side.equals("r"))
                {
                    thisNode24.setItem(thisNode24.getNumItems() - 1, null);
                    thisNode24.setNumItems(thisNode24.getNumItems() - 1);
                    NodeData d = p.getItem(i - 1);
                    sibling.insertItem(d);
                    p.disconnectChild(0);
                    p.connectChild(0, p.disconnectChild(1));
                    for (int j = i; j < p.getNumItems(); j++)
                    {
                        p.setItem(j - 1, p.getItem(j));
                        if (j + 1 <= p.getNumItems())
                        {
                            p.connectChild(j, p.disconnectChild(j + 1));
                        }
                    }
                    p.setItem(p.getNumItems() - 1, null);
                    p.setNumItems(p.getNumItems() - 1);
                    if (p.getNumItems() == 0)
                    {
                        if (p != root)
                        {
                            p = balancetree(p);
                        }
                        else
                        {
                            root = sibling;
                        }
                    }
                    return p;
                }
            }
        } else if (sibling.getNumItems() > 1)
        {
            int f ;
            if (sibling_side.equals("r"))
            {
                f = 0;
            }
            else
            {
                f = sibling.getNumItems() - 1;
            }
            for (int i = 0; i <= p.getNumItems(); i++)
            {
                if (p.getChild(i) == sibling && sibling_side.equals("l"))
                {
                    thisNode24.getItem(0).dData = p.getItem(i).dData;
                    p.getItem(i).dData = sibling.getItem(f).dData;
                    sibling.deletenodevalue(sibling.getItem(f).dData);
                    return p;
                }
                if (p.getChild(i) == sibling && sibling_side.equals("r"))
                {
                    thisNode24.getItem(0).dData = p.getItem(i - 1).dData;
                    p.getItem(i - 1).dData = sibling.getItem(f).dData;
                    sibling.deletenodevalue(sibling.getItem(f).dData);
                    return p;
                }
            }
        }
        return null;
    }
    public Node24 balancetree(Node24 currnode)
    {
        String sibling_side = "l";
        Node24 p = currnode.getParent();
        Node24 sibling = currnode.getsibiling("z");
        if (sibling == null)
        {
            sibling_side = "r";
            sibling = p.getChild(1);
        }
        if (sibling.getNumItems() == 1)
        {
            for (int i = 0; i <= p.getNumItems(); i++)
            {
                if (p.getChild(i) == sibling && sibling_side.equals("l"))
                {
                    NodeData d = p.getItem(i);
                    sibling.insertItem(d);
                    sibling.connectChild(sibling.getNumItems(),
                            currnode.disconnectChild(0));
                    p.disconnectChild(i + 1);
                    for (int j = i; j < p.getNumItems(); j++)
                    {
                        if (j + 1 < p.getNumItems())
                        {
                            p.setItem(j, p.getItem(j + 1));
                            if (j + 2 <= p.getNumItems())
                            {
                                p.connectChild(j + 1, p.disconnectChild(j + 2));
                            }
                        }
                    }
                    p.setItem(p.getNumItems() - 1, null);
                    p.setNumItems(p.getNumItems() - 1);
                    if (p.getNumItems() == 0)
                    {
                        if (p != root)
                        {
                            p = balancetree(p);
                        }
                        else
                        {
                            root = sibling;
                        }
                    }
                    return p;
                }
                else if (p.getChild(i) == sibling && sibling_side.equals("r"))
                {
                    NodeData d = p.getItem(i - 1);
                    sibling.insertatfront(d);
                    sibling.connectChild(0, currnode.disconnectChild(0));
                    p.disconnectChild(0);
                    p.connectChild(0, p.disconnectChild(1));
                    for (int j = i; j < p.getNumItems(); j++)
                    {
                        p.setItem(j - 1, p.getItem(j));
                        if (j + 1 <= p.getNumItems())
                        {
                            p.connectChild(j, p.disconnectChild(j + 1));
                        }
                    }
                    p.setItem(p.getNumItems() - 1, null);
                    p.setNumItems(p.getNumItems() - 1);
                    if (p.getNumItems() == 0)
                    {
                        if (p != root)
                        {
                            p = balancetree(p);
                        }
                        else
                        {
                            root = sibling;
                        }
                    }
                    return p;
                }
            }
        }
        else if (sibling.getNumItems() > 1)
        {
            int f ;
            if (sibling_side.equals("r"))
            {
                f = 0;
            }
            else
            {
                f = sibling.getNumItems() - 1;
            }
            for (int i = 0; i <= p.getNumItems(); i++)
            {
                if (p.getChild(i) == sibling && sibling_side.equals("l"))
                {
                    currnode.setNumItems(currnode.getNumItems()+1);
                    currnode.connectChild(1, currnode.disconnectChild(0));
                    currnode.connectChild(0,
                            sibling.disconnectChild(sibling.getNumItems()));
                    currnode.setItem(0, p.getItem(i));
                    p.setItem(i, sibling.getItem(f));
                    sibling.setItem(sibling.getNumItems() - 1, null);
                    sibling.setNumItems(sibling.getNumItems() - 1);
                    return p;
                }
                if (p.getChild(i) == sibling && sibling_side.equals("r"))
                {
                    currnode.setNumItems(currnode.getNumItems()+1);
                    currnode.setItem(0, p.getItem(i - 1));
                    p.setItem(i - 1, sibling.getItem(f));
                    currnode.connectChild(1, sibling.disconnectChild(f));
                    for (int j = 0; j < sibling.getNumItems(); j++)
                    {
                        if (j + 1 < sibling.getNumItems())
                        {
                            sibling.setItem(j, sibling.getItem(j + 1));
                        }
                        sibling.connectChild(j, sibling.disconnectChild(j + 1));
                    }
                    sibling.setItem(sibling.getNumItems() - 1, null);
                    sibling.setNumItems(sibling.getNumItems() - 1);
                    return p;
                }
            }
        }
        return null;
    }
    public Node24 getinordernode(Node24 thisNode24)
    {
        Node24 c ;
        if(thisNode24.isLeaf())
        {
            c = thisNode24;
        }
        else
        {
            c = getinordernode(thisNode24.getChild(0));
        }
        return c;
    }
}
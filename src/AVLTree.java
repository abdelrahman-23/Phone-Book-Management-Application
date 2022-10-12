public class AVLTree
{
    private Node root;
    public AVLTree()
    {
        root = null;
    }
    public Node getRoot()
    {
        return root;
    }
    public void setRoot(Node root)
    {
        this.root = root;
    }
    public int height(Node node)
    {
        if (node == null)
            return 0;
        return node.getHeight();
    }
    public int getBalance(Node node)
    {
        if (root == null)
            return 0;
        return height(node.getLeft()) - height(node.getRight());
    }
    public int maxHeight(int leftHeight, int rightHeight)
    {
        return Math.max(leftHeight, rightHeight);
    }
    public Node rightRotate(Node grandParent)
    {
        Node parent = grandParent.getLeft();
        Node child = parent.getRight();
        parent.setRight(grandParent);
        grandParent.setLeft(child);
        grandParent.setHeight(maxHeight(height(grandParent.getLeft()), height(grandParent.getRight())) + 1);
        parent.setHeight(maxHeight(height(parent.getLeft()), height(parent.getRight())) + 1);
        return parent;
    }
    public Node leftRotate(Node grandParent)
    {
        Node parent = grandParent.getRight();
        Node child = parent.getLeft();
        parent.setLeft(grandParent);
        grandParent.setRight(child);
        grandParent.setHeight(maxHeight(height(grandParent.getLeft()), height(grandParent.getRight())) + 1);
        parent.setHeight(maxHeight(height(parent.getLeft()), height(parent.getRight())) + 1);
        return parent;
    }
    public Node insert(Node node, String line,int sort)
    {
        if (line.equals("\n"))
            return node;
        String[] words = line.toLowerCase().split(",");
        String word = words[sort];
        if (sort==0)
        {
            word=words[0];
        }
        if (sort==1)
        {
            word=words[1];
        }
        if (sort==2)
        {
            word=words[0]+words[1];
        }
        if (node == null)
            return (new Node(word,words[0],words[1],words[2],words[3],words[4]));
        if (word.compareTo(node.getElement()) == 0) {
            node.setCount(node.getCount() + 1);
        }
        if (word.compareTo(node.getElement()) < 0)
            node.setLeft(insert(node.getLeft(), line,sort));
        else if (word.compareTo(node.getElement()) > 0)
            node.setRight(insert(node.getRight(), line,sort));
        else
            return node;
        node.setHeight(1 + maxHeight(height(node.getLeft()), height(node.getRight())));
        int balance = getBalance(node);
        if ((balance > 1) && (word.compareTo(node.getLeft().getElement())) < 0)
            return rightRotate(node);
        if ((balance < -1) && (word.compareTo(node.getRight().getElement())) > 0)
            return leftRotate(node);
        if ((balance > 1) && (word.compareTo(node.getLeft().getElement())) > 0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }
        if ((balance < -1) && (word.compareTo(node.getRight().getElement())) < 0)
        {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }
        return node;
    }
    public int CalculateHeight(){
        return height(root);
    }
    public void deleteTree()
    {
        root = null;
    }
    public void inOrder(Node node)
    {
        if (node != null)
        {
            inOrder(node.getLeft());
            System.out.println(node.getline());
            inOrder(node.getRight());
        }
    }
    public boolean search(String word)
    {
        Node position = root;
        while (position != null)
        {
            if (word.compareTo(position.getElement()) < 0)
                position = position.getLeft();
            else if (word.compareTo(position.getElement()) > 0)
                position = position.getRight();
            else
                return true;
        }
        return false;
    }
    public Node returnNode(String word)
    {
        Node position = root;
        while (position != null)
        {
            if (word.compareTo(position.getElement()) < 0)
                position = position.getLeft();
            else if (word.compareTo(position.getElement()) > 0)
                position = position.getRight();
            else
                return position;
        }
        return null;
    }
    public int getTotalNumberOfNodes() {
        return this.getTotalNumberOfNodes(this.getRoot());
    }

    private int getTotalNumberOfNodes(Node head)
    {
        if (head == null) {
            return 0;
        } else {
            int length = 1;
            length += this.getTotalNumberOfNodes(head.getLeft());
            length += this.getTotalNumberOfNodes(head.getRight());
            return length;
        }
    }
        public void remove(String FirstName)
        {
        Node parent = null;
        Node target = null;
        Node node = this.root;

        while(target == null && node != null)
        {
            if (FirstName.toLowerCase().compareTo(node.getElement()) < 0)
            {
                parent = root;
                node = node.getLeft();
            }
            else if (FirstName.compareTo(node.getElement()) > 0)
            {
                parent = node;
                node = node.getRight();
            }
            else
            {
                target = node;
            }
        }

        if (target == null)
        {
            System.out.println("Contact Not Found");
        }

        else
        {
            Node replacement ;
            if (target.getLeft() == null && target.getRight() == null) {
                if (target.getLeft() != null) {
                    replacement = target.getLeft();
                } else {
                    replacement = target.getRight();
                }
            } else {
                replacement = this.getReplacementRecursive(target.getLeft(), target, target);
            }

            if (replacement != null)
            {
                replacement.setLeft(target.getLeft());
                replacement.setRight(target.getRight());
                this.updateHeight(replacement);
            }

            if (this.root == target)
            {
                this.root = replacement;
            }
            else
            {
                assert parent != null;
                if (parent.getLeft() == target)
                {
                    parent.setLeft(replacement);
                }
                else
                {
                    parent.setRight(replacement);
                }
            }
            System.out.println("Contact Deleted Successfully !");
        }
    }
    private Node getReplacementRecursive(Node node, Node parent, Node target) {
        if (node == null) {
            return null;
        } else {
            Node replacement = this.getReplacementRecursive(node.getRight(), node, target);
            if (parent.getRight() == replacement) {
                parent.setRight((Node)null);
                if (parent.getLeft() == null && parent != target) {
                    parent.setHeight(parent.getHeight() - 1);
                }
            } else {
                this.updateHeight(parent);
            }

            return replacement;
        }
    }
    private void updateHeight(Node node)
    {
        int iLeftHeight = -1;
        int iRightHeight = -1;
        if (node.getLeft() != null)
        {
            iLeftHeight = node.getLeft().getHeight();
        }
        if (node.getRight() != null)
        {
            iRightHeight = node.getRight().getHeight();
        }
        node.setHeight(max(iLeftHeight, iRightHeight) + 1);
    }
    public static int max(int first, int second)
    {
        return Math.max(first, second);
    }
}
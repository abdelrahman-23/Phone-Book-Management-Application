import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class PhoneBookMangerAVL
{
    public static void clearAll(AVLTree tree)
    {
        if (tree.getRoot() == null)
        {
            System.out.println("Tree is already empty .");
            return;
        }
        tree.deleteTree();
        System.out.println("All Contacts removed from the Tree.");
    }
    public static void contains(String element, AVLTree tree)
    {
        System.out.println("Does this Contact contain \"" + element + "\" ?");
        if (tree.search(element))
            System.out.println(tree.returnNode(element).getline() + " .");
        else
            System.out.println("No, there is not such a Contact like \"" + element + "\".");
    }
    public static void main_test() throws IOException
    {
        long start=System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        String menu = """
                1) Insert\s
                2) Display all contacts by inorder traversal.\s
                3) Search by\s
                4) Clear All Contacts\s
                5) Height Of Tree\s
                6) Number Of Nodes\s
                7) RUNTIME\s
                8) Delete Element\s
                9) Close the tree & choose another\s
                10) EXIT Program""";
        String formatMenu = "1).csv file \n2) Manually typing \n3) CANCEL";
        AVLTree tree = new AVLTree();
        System.out.println("---Phone Book Management Application by AVL---\nSELECT OPERATOR\n" + menu);
        String choiceAVL = sc.nextLine();
        System.out.println("Choose index to sort by it (0= first name,1 = last name,2= full name)");
        int sort =sc.nextInt();
        String sorts = null;
        if (sort==0)
        {
            sorts="First Name";
        }
        else if (sort==1)
        {
            sorts="Last Name";
        }
        else if (sort==2)
        {
            sorts="Full Name";
        }
        label:
        while (true)
        {
            switch (choiceAVL)
            {
                case "1":
                    System.out.println("Choose option\n" + formatMenu);
                    String format = sc.next();
                    while (true)
                    {
                        switch (format)
                        {
                            case "1" ->
                            {
                                String filepath="C:\\Users\\Abdelrahman Raslan\\IdeaProjects\\Final_DataStructure_Project2022\\src\\DataProject.csv";
                                try (BufferedReader r = new BufferedReader(new FileReader(filepath)))
                                {
                                    int i = 1;
                                    String line = r.readLine();
                                    System.out.println("---Phone Contacts---");
                                    while (line != null)
                                    {
                                        line = r.readLine();
                                        if (line != null)
                                        {
                                            System.out.print("\nLine " + i + ": " + line);
                                            tree.setRoot(tree.insert(tree.getRoot(), line, sort));
                                        }
                                        i++;
                                    }
                                    System.out.println("\nINSERTING......");
                                    System.out.println("\nInserting Done");
                                }
                                catch (IOException e)
                                {
                                    throw new RuntimeException(e);
                                }
                            }
                            case "2" ->
                            {
                                System.out.println("Enter Node Manually : ");
                                sc = new Scanner(System.in);
                                String manualText = sc.nextLine();
                                tree.setRoot(tree.insert(tree.getRoot(), manualText, sort));
                                System.out.println("\nInserting Done");
                            }
                            case "3" -> System.out.println("Returning to main menu...");
                            default ->
                            {
                                System.out.println("Wrong Operation\n\nPlease try again correct operator:\n" + formatMenu);
                                format = sc.next();
                                continue;
                            }
                        }
                        break;
                    }
                    break;
                case "2":
                    System.out.println("---Contact inOrderTraversal by "+sorts+"---");
                    tree.inOrder(tree.getRoot());
                    break;
                case "3":
                    System.out.print("Enter the "+sorts+" you want to search by : ");
                    String word = sc.next().toLowerCase();
                    contains(word, tree);
                    break;
                case "4":
                    clearAll(tree);
                    break;
                case "5":
                    System.out.println("Height Of tree : " +tree.CalculateHeight());
                    break ;
                case "6":
                    System.out.println("Number of Nodes : "+tree.getTotalNumberOfNodes());
                    break ;
                case "7":
                        long end=System.currentTimeMillis();
                        System.out.println("runtime in seconds : "+(end-start)/1000 +" sec");
                        break ;
                case "8":
                         System.out.println("Enter "+sorts+" You Want to delete");
                         String del=sc.next();
                         tree.remove(del);
                         break ;
                case "9":
                        System.out.println("GOOD BYE");
                        break label;
                case "10":
                    System.out.println("Thanks!! Best Regards, ARAS ");
                    System.exit(0);
                default:
                    System.out.println("Wrong Operation\n\nPlease try again correct operator:\n" + menu);
                    choiceAVL = sc.nextLine();
                    continue;
            }
            System.out.println("\nSELECT NEXT OPERATOR:\n" + menu);
            choiceAVL = sc.nextLine();
        }
    }

}
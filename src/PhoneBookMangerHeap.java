import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
public class PhoneBookMangerHeap
{
    public static void main_Heap() throws IOException
    {
        long start=System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        String menu = """
                1) Insert by csv File\s
                2) Display all contacts by inorder traversal.\s
                3) Search by\s
                4) Remove Root\s
                5) Height Of Tree\s
                6) Number Of Nodes\s
                7) RUNTIME\s
                8) Delete Element\s
                9) Display Heap\s
                10) Close the tree & choose another\s
                11) EXIT Programme""";
        Heap phoneBook = new Heap(1000);
        System.out.println("---Phone Book Management Application by Heap---\nSELECT OPERATOR\n" + menu);
        String choiceHeap = sc.nextLine();
        String[] names ;
        String[] phone;
        String[] email ;
        String[] address;
        String[] data ;
        int count;
        BufferedReader reader ;
        String line ;
        label:
        while (true)
        {
            switch (choiceHeap)
            {
                case "1":
                    Path countLines = Paths.get("C:\\Users\\Abdelrahman Raslan\\IdeaProjects\\Final_DataStructure_Project2022\\src\\AASR2.csv");
                    count = (int) Files.lines(countLines).count();
                    names = new String[count-1];
                    phone = new String[count-1];
                    email = new String[count-1];
                    address = new String[count-1];
                    reader = new BufferedReader(new FileReader("C:\\Users\\Abdelrahman Raslan\\IdeaProjects\\Final_DataStructure_Project2022\\src\\AASR2.csv"));
                    reader.readLine();
                    for (int i =0 ; i<=count-2 ; i++)
                    {
                        line = reader.readLine();
                        data = line.split(",");
                        names[i] = data[0];
                        phone[i] = data[1];
                        email[i] = data[2];
                        address[i] = data[3];
                    }
                    reader.close();
                    for (int i =0 ; i<=count-2 ;i++)
                    {
                        NodeHeap obj = new NodeHeap(names[i].substring(0, names[i].indexOf(" ")),names[i].substring(names[i].indexOf(" ")+1, names[i].length()-1), email[i], address[i] , phone[i]);
                        phoneBook.insertNode(obj);
                    }
                case "2":
                    System.out.println("---Contact inOrderTraversal by Name---");
                    phoneBook.inorderDisplay(1);
                    break;
                case "3":
                    System.out.print("Enter the name you want to search by : ");
                    String word = sc.next();
                    phoneBook.search(word);
                    break;
                case "4":
                    System.out.println("REMOVING ROOT.....");
                    phoneBook.removeRoot();
                    break;
                case "5":
                    phoneBook.printTreeInfo_hight();
                    break ;
                case "6":
                    phoneBook.printTreeInfo_node();
                    break ;
                case "7":
                    long end=System.currentTimeMillis();
                    System.out.println("runtime in seconds : "+(end-start)/1000 +" sec");
                    break ;
                case "8":
                    System.out.println("Enter Name You Want to delete");
                    String del=sc.next();
                    phoneBook.deleteContact("Lucy Littl");
                    break ;
                case "9":
                    phoneBook.displayHeap();
                    break ;
                case "10":
                    System.out.println("GOOD BYE");
                    break label;
                case "11":
                    System.out.println("Thanks!! Best Regards, ARAS ");
                    System.exit(0);
                default:
                    System.out.println("Wrong Operation\n\nPlease try again correct operator:\n" + menu);
                    choiceHeap = sc.next();
                    continue;
            }
            System.out.println("\nSELECT NEXT OPERATOR:\n" + menu);
            choiceHeap = sc.next();
        }
    }
}
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class PhoneBookManger24
{
    public static void main_24test()  {
        long start=System.currentTimeMillis();
        TwoFour theTree = new TwoFour();
        Scanner s24c=new Scanner(System.in);
        String line;
        String splitBy = ",";
        String menu = """
                1) Insert\s
                2) Display all contacts by inorder traversal & Show Height .
                3) Search by\s
                4) RUNTIME\s
                5) Delete Element\s
                6) Close the tree & choose another\s
                7) EXIT Programme""";
        System.out.println("---Phone Book Management Application by 2-4 Tree---\nSELECT OPERATOR\n" + menu);
        String choice24 = s24c.nextLine();
        String formatMenu = "1).csv file \n2) Manually typing \n3) CANCEL";
        Scanner input=new Scanner(System.in);
        System.out.println("Choose index to sort by it (0= first name,1 = last name,2= full name)");
        int sort =input.nextInt();
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
            try
            {
                switch (choice24)
                {
                    case "1":
                        System.out.println("Choose option\n" + formatMenu);
                        String format = input.next();
                        while (true) {
                            switch (format)
                            {
                                case "1" ->
                                {
                                    try
                                    {
                                        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Abdelrahman Raslan\\IdeaProjects\\Final_DataStructure_Project2022\\src\\DataProject.csv"));
                                        while ((line = br.readLine()) != null)
                                        {
                                            String[] person = line.split(splitBy);
                                            theTree.insert(sort,person[0], person[1], person[2], person[3], person[4]);
                                        }
                                        System.out.println("\nINSERTING......");
                                        System.out.println("\nInserting Done");
                                    }
                                    catch (IOException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                case "2" ->
                                {
                                    System.out.println("---Enter Manually---");
                                    System.out.print("Enter First Name : \n");
                                    System.out.println();
                                    String FirstName = input.next();
                                    System.out.println();
                                    System.out.print("Enter Last Name : \n");
                                    System.out.println();
                                    String LastName = input.next();
                                    System.out.println();
                                    System.out.println("Enter Email Address : \n");
                                    System.out.println();
                                    String email = input.next();
                                    System.out.println();
                                    System.out.println("Enter Address : \n");
                                    System.out.println();
                                    String address = input.next();
                                    System.out.println();
                                    System.out.println("Enter Phone Number : \n");
                                    System.out.println();
                                    String phonenumber = input.next();
                                    System.out.println();
                                    Node24 present = theTree.find(FirstName);
                                    if (present != null)
                                    {
                                        System.out.println("Data Already present!!");
                                    }
                                    else
                                    {
                                        theTree.insert(sort,FirstName, LastName, phonenumber, email, address);
                                    }
                                }
                                case "3" -> System.out.println("Returning to main menu...");
                                default ->
                                {
                                    System.out.println("Wrong Operation\nPlease try again correct operator:\n" + formatMenu);
                                    format = input.next();
                                    continue;
                                }
                            }
                            break;
                        }
                        break;
                    case "2":
                        System.out.println("Tree: ");
                        theTree.displayTree(0);
                        break;
                    case "3":
                        System.out.print("\nEnter "+sorts+" to find: \n");
                        String Name=input.next();
                        Node24 found = theTree.find(Name);
                        if (found != null)
                        {
                            System.out.println("Found " + Name);
                        }
                        else
                            System.out.println("Could not find " + Name);
                        break;
                    case "4":
                        long end=System.currentTimeMillis();
                        System.out.println("runtime in seconds : "+(end-start)/1000 +" sec");
                        break ;
                    case "5":
                        System.out.print("\nEnter "+sorts+" to delete: \n");
                        Name=input.next();
                        Node24 del = theTree.find(Name);
                        if (del != null)
                        {
                            if(theTree.delete(del,Name)!=null)
                                System.out.println("Deleted" + Name);
                            else
                            {
                                System.err.println("Not Deleted!!!");
                            }
                        }
                        else
                            System.out.println("Could not find " + Name);
                        break;
                    case "6":
                        System.out.println("GOOD BYE");
                        break label;
                    case "7":
                        System.out.println("Thanks!! Best Regards, ARAS ");
                        System.exit(0);
                    default:
                        System.out.println("Invalid entry\n");
                        System.out.println("\nSELECT NEXT OPERATOR:\n" + menu);
                        choice24 =s24c.nextLine();
                }
                System.out.println("\nSELECT NEXT OPERATOR:\n" + menu);
                choice24 =s24c.nextLine();
            }
            catch (Exception e)
            {
                System.err.println("Please enter valid input");
            }
        }
    }
}
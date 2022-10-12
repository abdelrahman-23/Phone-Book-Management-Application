import java.util.Scanner;
import java.io.*;
public class PHONEManger
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("----MADE BY----\nABDELRAHMAN RASLAN\nREWAN SALAH\nAML IBRAHIM\nSUHAILA ADEL");
        System.out.println("---Welcome To our Phone Book Management Application---");
        System.out.println(" 1) Show Contact file\n 2) AVL Tree\n 3) 2-4 Tree\n 4) Heap\n 5) EXIT");
        Scanner t = new Scanner(System.in);
        int option = t.nextInt();
        int i =0;
        while (true){
        if (option==1)
        {
        System.out.println("UPLOADING FILE.........");
        Scanner sc = new Scanner(new File("C:\\Users\\Abdelrahman Raslan\\IdeaProjects\\Final_DataStructure_Project2022\\src\\DataProject.csv"));
        sc.useDelimiter(",");
        while (sc.hasNext())
        {
                System.out.print(sc.next() + " ");
        }
        sc.close();
        }
        else if (option == 2 )
        {
            System.out.println("AVL Tree");
            PhoneBookMangerAVL.main_test();
        }
        else if (option == 3)
        {
            System.out.println("2-4 Tree");
            PhoneBookManger24.main_24test();
        }
        else if (option == 4)
        {
            System.out.println("heap");
            PhoneBookMangerHeap.main_Heap();
        } else if (option==5) {
            break;

        }
        else {
            System.out.println("Wrong Operation\n\nPlease try again correct operator\n");
            System.out.println(" 1) Show Contact file\n 2) AVL Tree\n 3) 2-4 Tree\n 4) Heap\n 5) EXIT");
            option = t.nextInt();
        }
        i++;
        String count = "";
        if (i==1){
            count="FIRST";
        } else if (i==2) {
            count="SECOND";
        }
        else if (i==3){
            count="THIRD";
        }
        else if (i==4){
            count="FOURTH";
        } else if (i==5) {
            count="FIFTH";
        }
            System.out.println("\n\n---"+count+" OPERATOR DONE SUCCESSFULLY ^_^ ---");
        System.out.println("---SELECT ANOTHER OPERATOR---");
        System.out.println(" 1) Show Contact file\n 2) AVL Tree\n 3) 2-4 Tree\n 4) Heap\n 5) EXIT");
        option = t.nextInt();
        }
        System.out.println("Thanks!! Best Regards, ARAS ");
    }
}
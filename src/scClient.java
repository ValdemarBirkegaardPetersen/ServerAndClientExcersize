import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class scClient {
    //Declaring data streams, input, a boolean and a the port we use
    public static DataOutputStream outputStream1 = null;
    public static DataOutputStream outputStream2 = null;
    public static DataOutputStream outputStream3 = null;
    public static DataInputStream inputStream1 = null;
    public static DataInputStream inputStream2 = null;
    public static int loanAmount;
    public static int loanLength;
    public static double annualRate;
    public static double monthlyPayment;
    public static double totalPayment;
    public static boolean b = true;
    public static int port  = 8102;


    public static void main(String[] args) throws IOException {



        //For loop for re-running
        while (b){
            //Creating socket with matching port
            Socket socket = new Socket("localhost", port);

            //Scanning for the loan amount
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter loan amount: ");

            //Outputting loanAmount input
            loanAmount = scan.nextInt();
            outputStream1 = new DataOutputStream(socket.getOutputStream());
            outputStream1.writeInt(loanAmount);

            //Outputting loanLength input
            System.out.println("Enter length of loan (years): ");
            loanLength = scan.nextInt();
            outputStream2 = new DataOutputStream(socket.getOutputStream());
            outputStream2.writeInt(loanLength);

            //Outputting annualRate input
            System.out.println("Enter annual rate: ");
            annualRate = scan.nextDouble();
            outputStream3 = new DataOutputStream(socket.getOutputStream());
            outputStream3.writeDouble(annualRate);

            //Declaring input streams so we can get calculated values from server so we can print and show it
            inputStream1 = new DataInputStream(socket.getInputStream());
            inputStream2 = new DataInputStream(socket.getInputStream());

            //Reading inputs from server
            monthlyPayment = inputStream1.readDouble();
            totalPayment = inputStream2.readDouble();

            //Printing newly received inputs from server
            System.out.println("The monthly payment will be: " + monthlyPayment);
            System.out.println("The total payment will be: " + totalPayment);

            //Ask if the user wanna run the program again. If Yes change port by +1 and run again. Else shut down the program
            System.out.println("Do you wanna continue and do it again? Write 'Yes in chat");
            Scanner scanContinue = new Scanner(System.in);
            String con = "";
            con = scanContinue.nextLine();


            if (con.equals("Yes")|| con.equals("yes")) {
                port = port + 1;
                b = true;
                } else {
                b = false;
                System.out.println("Exiting program");
                break;
        }
        }
    }
}

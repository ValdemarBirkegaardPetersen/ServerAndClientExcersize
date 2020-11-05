import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class netServer  {
    public static int loanAmount = 0;
    public static int loanLength;
    public static double annualRate;
    public static boolean b = true;
    public static int port  = 8102;

    public static void main(String[] args) throws IOException {

        //Making a while-loop, which will enable the program to run itself over and over
        while(b) {

        //Creating the server's socket
        ServerSocket serverSocket = new ServerSocket(port);

        //Look for connection
        Socket socket = serverSocket.accept();

        //If connection is made, print this
        if(socket != null) {
            System.out.println("New client entered at: " + ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));
        }


        //Declaring all data streams
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        DataInputStream inputStream2 = new DataInputStream(socket.getInputStream());
        DataInputStream inputStream3 = new DataInputStream(socket.getInputStream());
        DataOutputStream outputStream1 = new DataOutputStream(socket.getOutputStream());
        DataOutputStream outputStream2 = new DataOutputStream(socket.getOutputStream());

        //Reading input from client console and putting it into variables
        loanAmount = inputStream.readInt();
        loanLength = inputStream2.readInt();
        annualRate = inputStream3.readDouble();

        //Arithmetic
        double monthlyInterestRate = annualRate / 1200;
        double monthlyPayment = loanAmount * monthlyInterestRate /  (1 - (1 / Math.pow(1 + monthlyInterestRate, loanLength * 12)));
        double totalPayment = monthlyPayment * loanLength * 12;

        //Outputting the now calculated values
        outputStream1.writeDouble(monthlyPayment);
        outputStream2.writeDouble(totalPayment);

        //Changing port (Must do this because it runs locally)
        port = port + 1;

        }

    }












}






import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerSide {
    
    private static final int PORT = 1722;

    public void downFile(String fileName) {
        try {

            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket socket = serverSocket.accept();
            System.out.println("Established a socket link");
            DataInputStream inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            byte[] buffer = new byte[1024];

            DataOutputStream fileOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
            System.out.println("Start receiving files!" + "\n");
            while((inputStream.read(buffer)) != -1) {
                fileOut.write(buffer);
            }
            System.out.println("Received, save the file as: " + fileName);
            fileOut.close();

        } catch (Exception exp) {
            System.out.println("EXCEPTION OCCURED");
            exp.printStackTrace();
            return;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the file name of copied file");
        String fileName = sc.nextLine();

        ServerSide server = new ServerSide();
        server.downFile(fileName);
    }

}

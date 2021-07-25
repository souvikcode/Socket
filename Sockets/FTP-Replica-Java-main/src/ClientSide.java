import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientSide {

    private static final String HOST = "localhost";
    private static final int PORT = 1722;

    public void uploadFile(String filename) {
        try {
            Socket socket = new Socket(HOST, PORT);
            File file = new File(filename);
            System.out.println("File length: " + (int) file.length());
            DataInputStream fileIn = new DataInputStream(new FileInputStream(filename));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            byte[] buffer = new byte[1024];

            while(fileIn.read(buffer) != -1) {
                out.write(buffer);
            }

            out.flush();
            fileIn.close();
            out.close();
            socket.close();
            System.out.println("File transfer completed");

        } catch (Exception exp) {
            System.out.println("EXCEPTION OCCURRED");
            exp.printStackTrace();
        }
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the file path");
        String fileName = sc.nextLine();

        ClientSide client = new ClientSide();
        client.uploadFile(fileName);
    }

}

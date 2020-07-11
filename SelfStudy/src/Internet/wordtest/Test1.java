package Internet.wordtest;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.function.BiFunction;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-16 0:41
 */
public class Test1 {

    @Test
    public void testClient() {
        System.out.println("Try to Connetct to 127.0.0.1:10000");

        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 10000);
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("The Server Connected!");
        System.out.println("Please enter some Character:");

        Scanner scanner = null;
        InputStream inputStream = null;
        PrintWriter pw = null;
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new InputStreamReader(System.in));

            pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println(bf.readLine());


            inputStream = socket.getInputStream();
            scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()){

                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            pw.close();

            try {
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

    @Test
    public void testServer(){
        Socket accept = null;
        BufferedReader bufferedReader = null;
        try {
            ServerSocket ss = new ServerSocket(10000);

            accept = ss.accept();
            String IP = accept.getInetAddress().getHostAddress();
            String port = ":" + accept.getLocalPort();
            System.out.println("A client come in !" + IP + port);

            InputStream inputStream = accept.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            System.out.println("Client send is :" + bufferedReader.readLine());

            OutputStream outputStream = accept.getOutputStream();
            outputStream.write("Your Message Received".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (bufferedReader  != null){

                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (accept != null){

                try {
                    accept.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

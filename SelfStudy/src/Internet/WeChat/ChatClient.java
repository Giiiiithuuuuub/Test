package Internet.WeChat;

import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-15 22:30
 */
public class ChatClient {
    public static void main(String[] args) {

        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 9999);

            Receive receive = new Receive(socket);
            Send send = new Send(socket);
            receive.start();
            send.start();
            send.join();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Test
    public void test(){
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 9999);

            Receive receive = new Receive(socket);
            Send send = new Send(socket);
            receive.start();
            send.start();
            send.join();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

class Send extends Thread{
    private Socket socket;

    public Send(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        Scanner scanner = null;
        try {
            OutputStream outputStream = socket.getOutputStream();
            PrintStream ps = new PrintStream(outputStream);
            scanner = new Scanner(System.in);

            while (true){
                System.out.print("自己的话：");
                String str = scanner.nextLine();
                if ("bye".equalsIgnoreCase(str)){
                    break;
                }
                ps.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null){

                scanner.close();
            }
        }

    }
}

class Receive extends Thread{
    private Socket socket;

    public Receive(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()){
                String message = scanner.nextLine();
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

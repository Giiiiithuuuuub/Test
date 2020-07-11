package Internet.WeChat;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-15 22:30
 */
public class ChatServer {

    static ArrayList<Socket> list = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(9999);

            while (true){
                Socket socket = ss.accept();
                list.add(socket);

                Message message = new Message(socket);
                message.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Message extends Thread{

        private Socket socket;
        private String ip;

        public Message(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                ip = socket.getInetAddress().getHostAddress();
                sendToOther(ip + ":  is online");

                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bf = new BufferedReader(inputStreamReader);
                String temp;
                while ((temp = bf.readLine()) != null){
                    sendToOther(ip + ":  " + temp);
                }

                sendToOther(ip + ":  " + "is offline");
            } catch (IOException e) {
                try {
                    sendToOther(ip + ":  is lost connection");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } finally {

                list.remove(socket);
            }
        }

        public void sendToOther(String message) throws IOException {

            for (Socket on : list) {

                OutputStream every = on.getOutputStream();
                PrintStream ps = new PrintStream(every);

                ps.println(message);
            }


        }
    }

}

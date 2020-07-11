package Internet.wordtest;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-16 1:10
 */
public class Test2 {
    @Test
    public void testClient() throws IOException {
//        System.out.println("Trying to connect 127.0.0.1:10000");
//        Socket socket = new Socket("localhost", 10000);
//        System.out.println("Connect Success !");
//        System.out.println("Please tell me what do you want to do?");
//
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//
//        OutputStream outputStream1 = socket.getOutputStream();
//        outputStream1
//
//        InputStream inputStream = socket.getInputStream();
//        FileOutputStream outputStream = new FileOutputStream("MyLife.jpg");
//
//
//
//        outputStream.close();
//        inputStream.close();
//        ps.close();
//        scanner.close();
//        socket.close();
    }

    @Test
    public void testServer() throws IOException {
        ServerSocket ss = new ServerSocket(10000);

        Socket accept = ss.accept();
        InputStream inputStream = accept.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        String str = null;
        while ((len = inputStream.read(bytes)) != -1){
            str = new String(bytes,0,len);
        }

        if (str.contains("Mylove")){
            FileInputStream fs = new FileInputStream("Mylove.jpg");
            OutputStream outputStream = accept.getOutputStream();
            byte[] bytes1 = new byte[1024];
            int len1;
            while ((len1 = fs.read(bytes1)) != -1){
                outputStream.write(bytes1,0,len);
            }
        }else {
            System.out.println("InputError");
        }
    }
}

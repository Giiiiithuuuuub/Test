package Internet;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-14 22:45
 */
public class TCPTest2 {

    @Test
    public void testClient() {

        ByteArrayOutputStream bao = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        Socket socket = null;
        try {
            FileInputStream fs = new FileInputStream("E:\\IDEA_workspace2_jdk1.8\\SelfStudy\\src\\Internet\\This is a file");
            InetAddress address = InetAddress.getByName("127.0.0.1");
            socket = new Socket(address, 9900);
            outputStream = socket.getOutputStream();
            byte[] temp = new byte[10];
            int len;
            while ((len = fs.read(temp)) != -1) {
                outputStream.write(temp, 0, len);
            }

            System.out.println("发送成功！");
            socket.shutdownOutput();

            inputStream = socket.getInputStream();

            bao = new ByteArrayOutputStream();

            byte[] temp1 = new byte[10];
            int len1;

            while ((len1 = inputStream.read(temp1)) != -1) {
                bao.write(temp1, 0, len1);
            }

            System.out.println(bao.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bao != null) {
                try {
                    bao.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void testServer() {

        OutputStream outputStream1 = null;
        ByteArrayOutputStream bao = null;
        FileOutputStream outputStream = null;
        InputStream inputStream = null;
        Socket accept = null;
        try {
            ServerSocket serverSocket = new ServerSocket(9900);

            accept = serverSocket.accept();

            inputStream = accept.getInputStream();

            bao = new ByteArrayOutputStream();

            outputStream = new FileOutputStream("I am IronMan.txt");
            byte[] temp = new byte[10];
            int len;
            while ((len = inputStream.read(temp)) != -1) {
                bao.write(temp, 0, len);
            }

            bao.writeTo(outputStream);

            System.out.println("已保存到本地！");

            outputStream1 = accept.getOutputStream();
            outputStream1.write("收到文件!".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (outputStream1 != null) {
                try {
                    outputStream1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bao != null) {
                try {
                    bao.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (accept != null) {
                try {
                    accept.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}

package Internet;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-14 22:30
 */
public class TCPTest1 {

    @Test
    public void testClient() {

        Socket socket = null;
        OutputStream outputStream = null;
        int port = 9999;
        try {
            InetAddress address = InetAddress.getByName("localhost");
            socket = new Socket(address, port);

            outputStream = socket.getOutputStream();

            outputStream.write("我是客户端，多多关照".getBytes());

            System.out.println("发送成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (outputStream != null){
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
    public void testServer(){

        ServerSocket serverSocket = null;
        Socket accept = null;
        InputStream inputStream = null;
        ByteArrayOutputStream bao = null;
        try {
            serverSocket = new ServerSocket(9999);

            accept = serverSocket.accept();

            inputStream = accept.getInputStream();

            bao = new ByteArrayOutputStream();

            byte[] temp = new byte[10];
            int len;
            while ((len = inputStream.read(temp)) != -1){
                bao.write(temp,0,len);
            }

            System.out.println(bao.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (bao != null){
                try {
                    bao.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStream != null){
                try {
                    inputStream.close();
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

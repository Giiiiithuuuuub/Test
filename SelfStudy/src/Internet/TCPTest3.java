package Internet;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.time.LocalDateTime;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-15 0:54
 */
public class TCPTest3 {

    @Test
    public void testClient() throws IOException {
        Socket socket = new Socket("localhost", 9999);

        InputStream inputStream = socket.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        System.out.println(br.readLine());

        OutputStream outputStream = socket.getOutputStream();

        PrintStream ps = new PrintStream(outputStream);
        ps.println("你好，我想报名就业班");

        System.out.println(br.readLine());

        ps.println("好吧，再见！");

        socket.close();

    }

    @Test
    public void testServer() throws IOException {
        ServerSocket ss = new ServerSocket(9999);

        Socket accept = ss.accept();

        OutputStream outputStream = accept.getOutputStream();

        PrintStream ps = new PrintStream(outputStream);
        ps.println("欢迎咨询尚硅谷");

        InputStream inputStream = accept.getInputStream();

        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));

        System.out.println(bf.readLine());

        ps.println("报满了，请报下一期吧");

        System.out.println(bf.readLine());

        accept.close();
    }

    @Test
    public void server1() throws IOException {
        ServerSocket server = new ServerSocket(9999); // 创建服务器
        while (true) {
            final Socket socket = server.accept(); // 接受客户端的请求
            new Thread() {
                public void run() {
                    try {
                        PrintStream ps = new PrintStream(socket.getOutputStream());
                        ps.println("欢迎咨询尚硅谷");
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        System.out.println(socket.getInetAddress().getHostAddress() + "留言：" + LocalDateTime.now());
                        System.out.println(br.readLine() + "\n");
                        ps.println("报满了,请报下一期吧");
                        System.out.println(socket.getInetAddress().getHostAddress() + "留言：" + LocalDateTime.now());
                        System.out.println(br.readLine() + "\n");
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

}

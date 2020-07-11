package Internet;

import org.junit.Test;

import java.io.IOException;
import java.net.*;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-14 23:20
 */
public class UDPTest {

    @Test
    public void testClient() throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();

        byte[] buffer = "hello".getBytes();

        InetAddress localhost = InetAddress.getByName("localhost");
        DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length, localhost, 9090);

        datagramSocket.send(datagramPacket);

        datagramSocket.close();
    }

    @Test
    public void testServer() throws IOException {

        DatagramSocket datagramSocket = new DatagramSocket(9090);

        byte[] buffer = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(buffer,0,buffer.length);

        datagramSocket.receive(datagramPacket);

        String str = new String(datagramPacket.getData(),0,datagramPacket.getLength());
        System.out.println(str);

        datagramSocket.close();

    }
}

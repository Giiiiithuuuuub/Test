package Internet;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-14 20:19
 */
public class InetAddressTest {
    @Test
    public void test1() throws UnknownHostException {
        InetAddress localhost = InetAddress.getByName("localhost");

        System.out.println(localhost);

        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);
    }
}

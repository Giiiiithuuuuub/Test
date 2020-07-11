package Internet;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-15 0:16
 */
public class URLTest {
    @Test
    public void testURL() throws IOException {

        URL url = new URL("http://localhost:8080/examples/Mylove.jpg");

//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//        InputStream inputStream = connection.getInputStream();
        InputStream inputStream = url.openStream();

        FileOutputStream fos = new FileOutputStream("Mylove1.jpg");

        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1){
            fos.write(buffer,0,len);
        }

        fos.close();
        inputStream.close();
//        connection.disconnect();


    }
}

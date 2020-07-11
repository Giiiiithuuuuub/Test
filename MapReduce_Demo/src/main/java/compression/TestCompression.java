package compression;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Test;
import sun.reflect.misc.ReflectUtil;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-21 7:43
 */
public class TestCompression {
    @Test
    public void testCompress() throws IOException, ClassNotFoundException {
        //文件系统对象
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        //待压缩文件
        String inpath = "E:\\BigData\\Exercise\\inputdmbj\\dmbj.txt";
        //压缩到哪儿
        String outPath = "E:\\BigData\\Exercise\\inputdmbj\\dmbj";
        //输入流
        FSDataInputStream in = fs.open(new Path(inpath));

        //用压缩输出流包装输出流
        Class<?> clazz = Class.forName("org.apache.hadoop.io.compress.DefaultCodec");
        CompressionCodec outCodec = (CompressionCodec) ReflectionUtils.newInstance(clazz, conf);
        CompressionOutputStream outputStream = outCodec.createOutputStream(fs.create(new Path(outPath + outCodec.getDefaultExtension())));//获取文件扩展名

        //拷贝
        IOUtils.copyBytes(in,outputStream,conf);

        //关闭资源
        IOUtils.closeStreams(in,outputStream);

    }

    @Test
    public void testDecompress() throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        String src = "E:\\BigData\\Exercise\\inputdmbj\\dmbj.deflate";
        String desc = "E:\\BigData\\Exercise\\inputdmbj\\dmbj.txt";
        //通过扩展名来获取编解码器，当然这里用上面反射的方式也可以，但是麻烦。
        CompressionCodec codec = new CompressionCodecFactory(conf).getCodec(new Path(src));
        CompressionInputStream inCodec = codec.createInputStream(fs.open(new Path(src)));

        FSDataOutputStream out = fs.create(new Path(desc));

        IOUtils.copyBytes(inCodec,out,conf);

        IOUtils.closeStreams(inCodec,out);
    }
}

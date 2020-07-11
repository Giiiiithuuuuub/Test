package yarn.review.compression;

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
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.util.ReflectionUtils;
import org.apache.kerby.config.Conf;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-07 19:57
 */
public class TestCompression {
    @Test
    public void testCompression() throws IOException, ClassNotFoundException {
        //1. get filesystem
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        //2. src path
        String srcPath = "E:\\BigData\\Exercise\\inputdmbj\\dmbj.txt";
        //3. desc path
        String descPath = "E:\\BigData\\Exercise\\inputdmbj\\dmbj";
        //4. get inputstream
        FSDataInputStream open = fs.open(new Path(srcPath));
        //5. get compression outputstream
        Class<?> clazz = Class.forName("org.apache.hadoop.io.compress.DefaultCodec");
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(clazz, conf);
        CompressionOutputStream outputStream = codec.createOutputStream(fs.create(new Path(descPath + codec.getDefaultExtension())));
        //6. copy bytes
        IOUtils.copyBytes(open,outputStream,conf);
        //7. close stream
        IOUtils.closeStreams(outputStream,open);
    }

    @Test
    public void testDecompression() throws IOException {

        //1. get filesystem
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        //2. src path
        String srcPath = "E:\\BigData\\Exercise\\inputdmbj\\dmbj.deflate";
        //3. desc path
        String descPath = "E:\\BigData\\Exercise\\inputdmbj\\dmbj.deflate.txt";
        //4. get compression inputstream
        CompressionCodec codec = new CompressionCodecFactory(conf).getCodec(new Path(srcPath));//根据扩展名获得编解码器
        CompressionInputStream inputStream = codec.createInputStream(fs.open(new Path(srcPath)));
        //5. get outputstream
        FSDataOutputStream outputStream = fs.create(new Path(descPath));
        //6. copy bytes
        IOUtils.copyBytes(inputStream, outputStream,conf);
        //7. close stream
        IOUtils.closeStreams(outputStream,inputStream);
    }
}

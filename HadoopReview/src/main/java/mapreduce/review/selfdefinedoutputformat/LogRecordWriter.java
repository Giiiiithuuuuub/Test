package mapreduce.review.selfdefinedoutputformat;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-03 23:16
 */
public class LogRecordWriter extends RecordWriter<Text, NullWritable> {
    private FSDataOutputStream fsout1;
    private FSDataOutputStream fsout2;


    public LogRecordWriter(TaskAttemptContext job) throws IOException {
        //获取文件系统
        FileSystem fs = FileSystem.get(job.getConfiguration());
        //创建路径
        Path pathAtguigu = new Path("E:\\output\\atguigu.txt");
        Path pathOther = new Path("E:\\output\\other.txt");

        //创建流
        fsout1 = fs.create(pathAtguigu);
        fsout2 = fs.create(pathOther);

    }

    @Override
    public void write(Text key, NullWritable value) throws IOException, InterruptedException {
        if (key.toString().contains("atguigu")){
            fsout1.write(key.toString().getBytes());
        }else {
            fsout2.write(key.toString().getBytes());
        }

    }

    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        IOUtils.closeStream(fsout1);
        IOUtils.closeStream(fsout2);
    }
}

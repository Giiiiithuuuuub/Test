package myoutputformat;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-18 22:08
 */
public class LogRecordWriter extends RecordWriter<Text, NullWritable> {

    private TaskAttemptContext job;
    private String atguigu = "E:\\BigData\\Exercise\\output6\\atguigu.log";
    private String other = "E:\\BigData\\Exercise\\output6\\other.log";
    private FSDataOutputStream atguiguOut;
    private FSDataOutputStream otherOut;

    public LogRecordWriter(TaskAttemptContext job) throws IOException {
        this.job = job;
        FileSystem fs = FileSystem.get(job.getConfiguration());//这里不能new一个conf，因为这个配置是要使用之前的配置项，所以用这种方式获得。
        atguiguOut = fs.create(new Path(atguigu));
        otherOut = fs.create(new Path(other));//输出流设置在构造器中，只创建一次，否则的话，默认每次创建一个流都会把之前写出的数据覆盖，
                                                //当然，设置true可以实现追加效果，但是每次创建流还是不对的
    }

    @Override
    public void write(Text key, NullWritable value) throws IOException, InterruptedException {

        String string = key.toString();
        if (string.contains("atguigu")){
            atguiguOut.writeBytes(string + "\n\r");
        }else {
            otherOut.writeBytes(string + "\n\r");
        }

    }

    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        IOUtils.closeStreams(atguiguOut,otherOut);
    }
}

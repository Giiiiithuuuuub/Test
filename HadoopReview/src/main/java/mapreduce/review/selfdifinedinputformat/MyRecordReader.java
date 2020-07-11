package mapreduce.review.selfdifinedinputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-03 16:32
 */
public class MyRecordReader extends RecordReader<Text, BytesWritable> {
    FileSplit split;
    Configuration conf;
    Text outK = new Text();
    BytesWritable outV = new BytesWritable();
    boolean isFlag = true;
    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        //初始化这一步获得切片和配置信息，为了获取文件系统，进行一系列操作。
        this.split = (FileSplit)split;
        conf = context.getConfiguration();
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {

        if (isFlag){

            byte[] buf = new byte[(int) split.getLength()];

            Path path = split.getPath();
            FileSystem fileSystem = path.getFileSystem(conf);

            FSDataInputStream inputStream = fileSystem.open(path);

            //因为api限制，先将文件读取到缓冲区，再从缓冲区读取到内存中
            IOUtils.readFully(inputStream,buf,0,buf.length);

            outV.set(buf,0,buf.length);
            outK.set(split.getPath().toString());

            inputStream.close();

            isFlag = false;

            return true;
        }

        return false;
        //进来是ture，读取，返回true，进入map，再进来，是false，返回false，直接进入cleanup环节。
    }

    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return outK;
    }

    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return outV;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}

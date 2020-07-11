package MyFileInput;


import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description  自定义InputFormat类
 * @create 2020-04-16 14:00
 */
public class MyFileInputFormat extends FileInputFormat<Text, BytesWritable> {

    //1. 重写isSplitable方法，表示不能被切分，要按一整个文件输出
    @Override
    protected boolean isSplitable(JobContext context, Path filename) {
        return false;
    }

    //2. 重写createRecordReader方法，设计读取方式
    @Override
    public RecordReader<Text, BytesWritable> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {

        //1. 需要什么就提供什么，需要一个RecordReader类，就创建一个
        MyRecordReader recordReader = new MyRecordReader();

        //2. 提供给MyRecordReader类相关参数信息
        recordReader.initialize(split,context);
        return recordReader;
    }
}

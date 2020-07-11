package MyFileInput;


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
 * @Description 为了自定义InputFormat而提供的自定义类
 * @create 2020-04-16 14:06
 */
public class MyRecordReader extends RecordReader<Text, BytesWritable> {

    private FileSplit split;
    Configuration conf;
    Text outK = new Text();
    BytesWritable outV = new BytesWritable();
    boolean isProgress = true;

    //1. 进行初始化：因为要得到split和context，所以要去MyFileInputFormat中获取
    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        // 2. 从MyFileInputFormat中获取参数（参见MyFileInputFormat中的2）
        // 3. 初始化需要提供split和conf两个参数,所以去提供两个属性 -- 这一步记忆,因为为了后续获得文件系统
        this.split = (FileSplit) split;//因为知道是文件类型，所以属性直接声明为FileSplit而不是InputSplit
        conf = context.getConfiguration();
    }

    //4. 核心业务逻辑 -- 流程为重点
    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {

        if(isProgress){

            byte[] buf = new byte[(int) split.getLength()];//缓冲区容量设置为切片大小，刚好一次读完一个文件
            //1. 获取fs对象(通过切片)，才可以继续操作
            Path path = split.getPath();
            FileSystem fs = path.getFileSystem(conf);

            //2. 获取输入流
            FSDataInputStream inputStream = fs.open(path);

            //3. 将文件内容读取到缓冲区（因为API的限制原因）
            IOUtils.readFully(inputStream,buf,0,buf.length);//需要个buf，提供一个

            //4. 将缓冲区的文件输出

            outK.set(path.toString());
            outV.set(buf,0,buf.length);

            //5. 关闭资源
            IOUtils.closeStream(inputStream);//fs不能关，关了就会报错

            //6. 因为需要返回值，这个nextKeyValue是run方法中用来判断还有没有文件，之前TextInputFormat默认的时候是一行一行读取，有下一行就
            //返回treu，进入map方法，接着读，没有下一行就返回false。这里我们如果只设置true，它就一直在读同一个切片无法结束这个task，不可取，如果设置为false，
            // 就无法进入map方法，所以都不可取，这里就想到了设置一个标记：
            //当传进一个切片（一个文件）时，默认标记位是true，执行nextKeyValue内部方法，读取切片的数据，然后将标记类置为false，整体返回true(因为可能后面还有数据，其实是为了进入map方法处理数据)，最后进入map方法写出，
            //之后再读取，返回的标记位是false，则执行cleanup方法，一个task任务结束，然后第二个切片和task进入，重新判断为true，重复上述行为

            //所以就是说，整个nextKeyValue方法就是为了将一个切片的信息读取完整，全部传到map方法中，然后再去结束这个MapTask。之前使用TextInputFormat，
            //就是处理完一行，判断有没有下一行，有的话返回true，下一次进来接着处理一行，没有返回false，这样就相当于处理完了整个文件，而我们这个案例的
            //需求是一次读一个文件，所以一开始要返回false，让这些数据进入map，但是第二次就应该返回fanlse，因为我们一次处理完了全部数据，那么就应该使用标记
            //位，第一次返回true，进入map，再读取，什么都没干，返回的false，然后结束MapTask。之后下一个切片重新进来，就重复上述步骤。
            isProgress = false;

            return true;
        }

        return false;

    }

    //5. 获得当前key
    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        // 需要当前key，就创建当前key
        // Text outK = new Text();拿出去因为后面要用
        return outK;
    }

    //6. 获得当前value
    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        // 需要value，就创建value
        // BytesWritable outV = new BytesWritable();拿出去因为后面要用
        return outV;
    }

    //不需要
    @Override
    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }
    //不需要
    @Override
    public void close() throws IOException {

    }
}

package mapjoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-20 7:02
 */
public class MapJoinDriver {
    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(MapJoinDriver.class);
        job.setMapperClass(MapJoinMapper.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(0);//必须进行设置，才可以实现没有ReduceTask
        job.addCacheFile(new URI("file:///E:/BigData/Exercise/inputpd/pd.txt"));//一定要注意是add,用set则需要URI数组,而且file:///不能忘

        FileInputFormat.setInputPaths(job,new Path("E:\\BigData\\Exercise\\inputorder"));
        FileOutputFormat.setOutputPath(job,new Path("E:\\BigData\\Exercise\\output5"));
        job.waitForCompletion(true);
    }
}

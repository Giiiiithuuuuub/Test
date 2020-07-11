package mapjoin.test2;

import mapjoin.test2.MapJoinMapper1;
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
public class MapJoinDriver1 {
    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(MapJoinDriver1.class);
        job.setMapperClass(MapJoinMapper1.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(0);//必须进行设置，才可以实现没有ReduceTask

        FileInputFormat.setInputPaths(job,new Path("E:\\BigData\\Exercise\\inputorder"));
        FileOutputFormat.setOutputPath(job,new Path("E:\\BigData\\Exercise\\output3"));
        job.waitForCompletion(true);
    }
}

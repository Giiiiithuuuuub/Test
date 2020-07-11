package etltest;

import mapjoin.MapJoinDriver;
import mapjoin.MapJoinMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-20 8:07
 */
public class LogDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(LogDriver.class);
        job.setMapperClass(LogMapper.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(0);//必须进行设置，才可以实现没有ReduceTask

        FileInputFormat.setInputPaths(job,new Path("E:\\BigData\\Exercise\\inputweblog"));
        FileOutputFormat.setOutputPath(job,new Path("E:\\BigData\\Exercise\\output6"));
        job.waitForCompletion(true);
    }
}

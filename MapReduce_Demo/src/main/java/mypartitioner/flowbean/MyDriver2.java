package mypartitioner.flowbean;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-17 21:47
 */
public class MyDriver2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();
        Job job = new Job(conf);

        job.setJarByClass(MyDriver2.class);
        job.setMapperClass(MyMapper2.class);
        job.setReducerClass(MyReducer2.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean2.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean2.class);

        job.setNumReduceTasks(5);
        job.setPartitionerClass(MyPartitioner.class);

        FileInputFormat.setInputPaths(job,new Path("E:\\BigData\\Exercise\\inputphone"));
        FileOutputFormat.setOutputPath(job,new Path("E:\\BigData\\Exercise\\output"));

        job.waitForCompletion(true);
    }
}

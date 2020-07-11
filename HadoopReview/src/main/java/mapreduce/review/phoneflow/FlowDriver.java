package mapreduce.review.phoneflow;

import mapreduce.review.wordcount.WordCountDriver;
import mapreduce.review.wordcount.WordCountMapper;
import mapreduce.review.wordcount.WordCountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.sql.Driver;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-03 11:56
 */
public class FlowDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(FlowDriver.class);
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        FileInputFormat.setInputPaths(job,new Path("E:\\BigData\\Exercise\\inputphoneflow"));
        FileOutputFormat.setOutputPath(job,new Path("E:\\BigData\\Exercise\\output1"));

        job.waitForCompletion(true);
    }
}

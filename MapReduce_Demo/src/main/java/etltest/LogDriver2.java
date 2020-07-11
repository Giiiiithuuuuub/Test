package etltest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-20 14:18
 */
public class LogDriver2 {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(LogDriver2.class);

        job.setMapperClass(LogMapper2.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        job.setNumReduceTasks(0);

        FileInputFormat.setInputPaths(job, new Path("E:\\BigData\\Exercise\\inputweblog"));
        FileOutputFormat.setOutputPath(job, new Path("E:\\BigData\\Exercise\\output6"));

        job.waitForCompletion(true);
    }

}

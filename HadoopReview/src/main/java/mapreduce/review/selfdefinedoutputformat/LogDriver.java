package mapreduce.review.selfdefinedoutputformat;

import mapreduce.review.keyvaluetextinputformat.KVTextDriver;
import mapreduce.review.keyvaluetextinputformat.KVTextMapper;
import mapreduce.review.keyvaluetextinputformat.KVTextReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.sql.Driver;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-03 22:54
 */
public class LogDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(LogDriver.class);
        job.setMapperClass(LogMapper.class);
        job.setReducerClass(LogReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.setOutputFormatClass(LogOutputFormat.class);

        FileInputFormat.setInputPaths(job,new Path("E:\\BigData\\Exercise\\inputlog"));
        FileOutputFormat.setOutputPath(job,new Path("E:\\BigData\\Exercise\\output"));

        job.waitForCompletion(true);
    }
}

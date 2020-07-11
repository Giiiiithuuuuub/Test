package mapreduce.review.manyjob;

import mapreduce.review.keyvaluetextinputformat.KVTextDriver;
import mapreduce.review.keyvaluetextinputformat.KVTextMapper;
import mapreduce.review.keyvaluetextinputformat.KVTextReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-07 11:18
 */
public class OneDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(OneDriver.class);
        job.setMapperClass(OneMapper.class);
        job.setReducerClass(OneReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);


        FileInputFormat.setInputPaths(job,new Path("E:\\BigData\\Exercise\\inputabc"));
        FileOutputFormat.setOutputPath(job,new Path("E:\\BigData\\Exercise\\output1"));

        job.waitForCompletion(true);
    }
}

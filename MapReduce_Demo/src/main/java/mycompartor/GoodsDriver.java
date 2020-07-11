package mycompartor;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-18 20:15
 */
public class GoodsDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(GoodsDriver.class);

        job.setMapperClass(GoodsMapper.class);
        job.setReducerClass(GoodsReducer.class);

        job.setMapOutputKeyClass(GoodsBean.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(GoodsBean.class);
        job.setOutputValueClass(NullWritable.class);

        job.setGroupingComparatorClass(GoodsCompartor.class);
        FileInputFormat.setInputPaths(job, new Path("E:\\BigData\\Exercise\\inputgoods"));
        FileOutputFormat.setOutputPath(job, new Path("E:\\BigData\\Exercise\\output2"));
        job.waitForCompletion(true);

    }

}

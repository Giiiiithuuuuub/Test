package extention.topn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.checkerframework.checker.units.qual.C;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-20 16:57
 */
public class TopDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(TopDriver.class);
        job.setMapperClass(TopMapper.class);
        job.setReducerClass(TopReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(TopBean.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(TopBean.class);


        FileInputFormat.setInputPaths(job,new Path("E:\\BigData\\Exercise\\inputtop10"));
        FileOutputFormat.setOutputPath(job,new Path("E:\\BigData\\Exercise\\output5"));
        job.waitForCompletion(true);
    }
}

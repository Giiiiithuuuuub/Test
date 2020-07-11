package extention.mutualfriends;

import extention.topn.TopBean;
import extention.topn.TopDriver;
import extention.topn.TopMapper;
import extention.topn.TopReducer;
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
 * @create 2020-04-23 16:43
 */
public class OneDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(OneDriver.class);
        job.setMapperClass(OneMapper.class);
        job.setReducerClass(OneReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.setInputPaths(job,new Path("E:\\BigData\\Exercise\\inputfriends\\friends.txt"));
        FileOutputFormat.setOutputPath(job,new Path("E:\\BigData\\Exercise\\output"));
        job.waitForCompletion(true);
    }
}

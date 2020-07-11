package mapreduce.review.mantualfriends;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.sql.Driver;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-07 16:09
 */
public class TwoDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(TwoDriver.class);
        job.setMapperClass(TwoMapper.class);
        job.setReducerClass(TwoReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

//        job.setNumReduceTasks(0);

        FileInputFormat.setInputPaths(job,new Path("E:\\BigData\\Exercise\\output\\part-r-00000"));
        FileOutputFormat.setOutputPath(job,new Path("E:\\BigData\\Exercise\\outputqwer"));

        job.waitForCompletion(true);
    }
}

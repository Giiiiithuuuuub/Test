package mapreduce.review.nlinetextinputformat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-03 11:09
 */
public class WordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {

    private Text outK = new Text();
    private IntWritable outV = new IntWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String s = value.toString();
        String[] split = s.split(" ");

        for (String s1 : split) {
            outK.set(s1);
            context.write(outK,outV);
        }

    }
}

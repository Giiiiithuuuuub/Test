package mapreduce.review.manyjob;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-07 11:18
 */
public class TwoMapper extends Mapper<LongWritable, Text,Text, Text> {

    private Text outK = new Text();
    private Text outV = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("--");
        String name = split[0];

        String[] temp = split[1].split("\t");

        outK.set(name);
        outV.set(temp[0] + "-->" + temp[1]);

        context.getCounter("map","should be 9").increment(1);
        context.write(outK,outV);
    }
}

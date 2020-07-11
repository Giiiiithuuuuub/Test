package mapreduce.review.manyjob;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-07 11:18
 */
public class OneMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    private String name;
    private Text outK = new Text();
    private IntWritable outV = new IntWritable(1);

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        name = inputSplit.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] split = value.toString().split(" ");
        for (String s : split) {
            outK.set(s + "--" + name);
            context.getCounter("map","should be 16").increment(1);
            context.write(outK,outV);
        }
    }
}
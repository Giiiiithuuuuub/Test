package mypartitioner.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-17 22:19
 */
public class WordCountMapper extends Mapper<LongWritable,Text,Text, IntWritable> {

    private Text outK = new Text();
    private IntWritable outV = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String string = value.toString();

        String[] splits = string.split(" ");

        for (String split : splits) {

            outK.set(split);
            context.write(outK,outV);
        }
    }
}

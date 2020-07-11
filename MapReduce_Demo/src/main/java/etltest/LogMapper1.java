package etltest;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-20 8:20
 */
public class LogMapper1 extends Mapper<LongWritable, Text,Text, NullWritable> {
    private Text outK = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        context.getCounter("map","amount").increment(1);
        String line = value.toString();
        String[] split = line.split(" ");

        if (split.length > 11){
            outK.set(line);
            context.getCounter("map","result").increment(1);
            context.write(outK,NullWritable.get());
        }
    }

}


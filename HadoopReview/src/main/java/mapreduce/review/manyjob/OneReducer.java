package mapreduce.review.manyjob;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Map;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-07 11:18
 */
public class OneReducer extends Reducer<Text, IntWritable,Text,IntWritable> {
    private IntWritable outV = new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }

        context.getCounter("reduce","test").increment(1);
        outV.set(sum);
        context.write(key,outV);
    }
}

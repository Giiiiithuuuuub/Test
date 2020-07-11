package mapreduce.review.manyjob;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-07 11:18
 */
public class TwoReducer extends Reducer<Text,Text,Text, Text> {
    private Text outV = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuilder builder = new StringBuilder();

        for (Text value : values) {
            builder.append(value + " ");
        }

        outV.set(builder.toString());

        context.getCounter("reduce","should be 3").increment(1);
        context.write(key,outV);
    }
}

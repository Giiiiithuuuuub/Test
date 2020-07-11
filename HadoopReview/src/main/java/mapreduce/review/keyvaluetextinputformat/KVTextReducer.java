package mapreduce.review.keyvaluetextinputformat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-03 15:59
 */
public class KVTextReducer extends Reducer<Text, Text,Text,Text> {
    private Text outV = new Text();
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuilder builder = new StringBuilder();
        for (Text value : values) {
            builder.append(value.toString());
        }
        outV.set(builder.toString());
        context.write(key,outV);
    }
}

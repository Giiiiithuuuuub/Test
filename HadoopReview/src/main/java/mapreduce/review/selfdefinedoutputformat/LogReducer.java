package mapreduce.review.selfdefinedoutputformat;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-03 22:54
 */
public class LogReducer extends Reducer<Text, NullWritable,Text,NullWritable> {
    private Text outK = new Text();

    @Override
    protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        for (NullWritable value : values) {
            String s = key.toString();
            s = s + "\n";
            outK.set(s);
            context.write(outK,NullWritable.get());
        }
    }
}

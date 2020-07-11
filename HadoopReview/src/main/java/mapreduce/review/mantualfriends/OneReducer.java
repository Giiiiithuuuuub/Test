package mapreduce.review.mantualfriends;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-07 16:08
 */
public class OneReducer extends Reducer<Text,Text,Text,Text> {
    private Text outV = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuilder builder = new StringBuilder();

        for (Text value : values) {
            builder.append(value.toString()).append(",");
        }
        outV.set(builder.toString());

        context.write(key,outV);
    }
}

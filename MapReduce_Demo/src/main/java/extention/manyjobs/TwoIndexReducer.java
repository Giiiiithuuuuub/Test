package extention.manyjobs;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-20 16:09
 */
public class TwoIndexReducer extends Reducer<Text,Text,Text,Text> {

    private Text outV = new Text();
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        String temp = "";
        for (Text value : values) {
            temp += value + " ";
        }
        outV.set(temp);
        context.write(key,outV);
    }
}

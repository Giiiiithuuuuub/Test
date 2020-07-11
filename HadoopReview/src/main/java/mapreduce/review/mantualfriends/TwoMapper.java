package mapreduce.review.mantualfriends;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-07 16:09
 */
public class TwoMapper extends Mapper<LongWritable, Text, Text, Text> {

    private Text outK = new Text();
    private Text outV = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");

        String person = split[0];
        String[] friends = split[1].split(",");
        Arrays.sort(friends);

        for (int i = 0; i < friends.length - 1; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                outK.set(friends[i] + "-" + friends[j]);
                outV.set(person);
                context.write(outK,outV);
            }
        }

    }
}

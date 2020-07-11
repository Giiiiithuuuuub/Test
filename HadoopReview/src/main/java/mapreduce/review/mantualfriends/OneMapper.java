package mapreduce.review.mantualfriends;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-07 16:08
 */
public class OneMapper extends Mapper<LongWritable, Text,Text,Text> {

    private Text outK = new Text();
    private Text outV = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split(" ");
        String person = split[0];

        String[] split1 = split[1].split(",");
        for (String s : split1) {
            outK.set(s);
            outV.set(person);

            context.write(outK,outV);
        }
    }
}

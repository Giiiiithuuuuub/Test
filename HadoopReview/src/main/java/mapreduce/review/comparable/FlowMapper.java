package mapreduce.review.comparable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-03 11:56
 */
public class FlowMapper extends Mapper<LongWritable, Text,FlowBean, Text> {
    private Text outV = new Text();
    private FlowBean outK = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String s = value.toString();
        String[] split = s.split("\t");

        outK.setUpFlow(Integer.parseInt(split[1]));
        outK.setDownFlow(Integer.parseInt(split[2]));
        outK.setSumFlow(Integer.parseInt(split[3]));

        outV.set(split[0]);

        context.write(outK,outV);
    }
}

package mapreduce.review.partitioner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-03 11:56
 */
public class FlowMapper extends Mapper<LongWritable, Text,Text, FlowBean> {
    private Text outK = new Text();
    private FlowBean outV = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String s = value.toString();
        String[] split = s.split("\t");

        outK.set(split[1]);
        outV.setUpFlow(Integer.parseInt(split[split.length- 3]));
        outV.setDownFlow(Integer.parseInt(split[split.length - 2]));
        outV.setSumFlow();

        context.write(outK,outV);
    }
}

package FlowTest;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-15 22:35
 */
public class FlowMapper extends Mapper<LongWritable, Text,Text, FlowBean> {

    private Text outK = new Text();
    private FlowBean outV = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String string = value.toString();
        String[] split = string.split("\t");

        outK.set(split[1]);
        outV.setUpFlow(Long.parseLong(split[split.length - 3]));
        outV.setDownFlow(Long.parseLong(split[split.length - 2]));
        outV.setSumFlow(Long.parseLong(split[split.length - 3] + split[split.length - 2]));

        context.write(outK, outV);
    }
}

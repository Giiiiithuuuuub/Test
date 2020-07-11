package mycomparable1;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-15 22:35
 */
public class FlowMapper extends Mapper<LongWritable, Text,FlowBean, Text> {

    private Text outV = new Text();
    private FlowBean outK = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String string = value.toString();
        String[] split = string.split("\t");

        outV.set(split[1]);
        outK.setUpFlow(Long.parseLong(split[split.length - 3]));
        outK.setDownFlow(Long.parseLong(split[split.length - 2]));
        outK.setSumFlow();

        context.write(outK, outV);
    }
}

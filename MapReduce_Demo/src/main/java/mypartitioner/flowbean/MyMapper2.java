package mypartitioner.flowbean;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-17 21:47
 */
public class MyMapper2 extends Mapper<LongWritable, Text,Text,FlowBean2> {

    private FlowBean2 outV = new FlowBean2();
    private Text outK = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String string = value.toString();
        String[] splits = string.split("\t");

        outK.set(splits[1]);
        outV.setUpFlow(Long.parseLong(splits[splits.length - 3]));
        outV.setDownFlow(Long.parseLong(splits[splits.length - 2]));
        outV.setSumFlow();

        context.write(outK,outV);
    }
}

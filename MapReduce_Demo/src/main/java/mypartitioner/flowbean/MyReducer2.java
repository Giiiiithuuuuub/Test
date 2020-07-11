package mypartitioner.flowbean;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-17 21:47
 */
public class MyReducer2 extends Reducer<Text,FlowBean2,Text,FlowBean2> {

    private FlowBean2 outV = new FlowBean2();

    @Override
    protected void reduce(Text key, Iterable<FlowBean2> values, Context context) throws IOException, InterruptedException {

        long totalUpFlow = 0;
        long totalDownFlow = 0;
        long totalSumFlow = 0;

        for (FlowBean2 value : values) {

            totalUpFlow += value.getUpFlow();
            totalDownFlow += value.getDownFlow();
            totalSumFlow += value.getSumFlow();
        }

        outV.setUpFlow(totalUpFlow);
        outV.setDownFlow(totalDownFlow);
        outV.setSumFlow(totalSumFlow);

        context.write(key,outV);
    }
}

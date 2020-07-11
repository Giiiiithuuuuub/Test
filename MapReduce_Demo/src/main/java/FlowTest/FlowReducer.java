package FlowTest;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-15 22:35
 */
public class FlowReducer extends Reducer<Text,FlowBean,Text,FlowBean> {

    private FlowBean outV = new FlowBean();

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {

        long totalUpFlow = 0;
        long totalDownFlow = 0;
        long totalSumFlow = 0;

        for (FlowBean value : values) {
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

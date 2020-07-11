package mapreduce.review.partitioner;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-03 11:56
 */
public class FlowReducer extends Reducer<Text, FlowBean,Text, FlowBean> {

    private FlowBean outV = new FlowBean();
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {

        int totalup = 0 , totaldown = 0, totalsum = 0;
        for (FlowBean value : values) {

            totalup += value.getUpFlow();
            totaldown += value.getDownFlow();
            totalsum += value.getSumFlow();
        }

        outV.setUpFlow(totalup);
        outV.setDownFlow(totaldown);
        outV.setSumFlow(totalsum);

        context.write(key,outV);
    }
}
